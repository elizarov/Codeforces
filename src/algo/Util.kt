package algo

import com.sun.jna.platform.win32.Kernel32
import com.sun.jna.platform.win32.WinBase
import com.sun.jna.platform.win32.WinDef
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private class ProcessTimes(
    val real: Duration,
    val kernel: Duration,
    val user: Duration
)

private fun WinBase.FILETIME.toDuration(): Duration {
    val value = (dwHighDateTime.toULong() shl 32) + dwLowDateTime.toUInt()
    return (value.toLong() * 100).toDuration(DurationUnit.NANOSECONDS)
}

private fun timeProcess(commandLine: String) : ProcessTimes {
    val kernel32: Kernel32 = Kernel32.INSTANCE
    val startupInfo = WinBase.STARTUPINFO()
    val processInfo = WinBase.PROCESS_INFORMATION()
    val ok = kernel32.CreateProcess(null, commandLine, null, null, false, WinDef.DWORD(0L), null, null, startupInfo, processInfo)
    if (!ok) {
        error("""
            Failed to create process, error 0x${kernel32.GetLastError().toUInt().toString(16)}, command line:
            $commandLine
            """.trimIndent()
        )
    }
    val result = kernel32.WaitForSingleObject(processInfo.hProcess, Int.MAX_VALUE)
    if (result != 0) error("Failed to wait for process")
    val create = WinBase.FILETIME()
    val exit = WinBase.FILETIME()
    val kernel = WinBase.FILETIME()
    val user = WinBase.FILETIME()
    check(kernel32.GetProcessTimes(processInfo.hProcess, create, exit, kernel, user))
    kernel32.CloseHandle(processInfo.hProcess)
    kernel32.CloseHandle(processInfo.hThread)
    return ProcessTimes(
        real = exit.toDuration() - create.toDuration(),
        kernel = kernel.toDuration(),
        user = user.toDuration()
    )
}

fun timeProcess(block: () -> Unit) {
    val controlProp = "runTimedBlock"
    val controlValue = "yes"
    if (System.getProperty(controlProp) == controlValue) {
        block()
        return
    }
    val className = Exception().stackTrace[1].className
    val javaHome = System.getProperty("java.home")
    val javaClassPath = System.getProperty("java.class.path")
    val process = "$javaHome\\bin\\java.exe"
    val commandLine = "\"$process\" -cp \"$javaClassPath\" -D$controlProp=$controlValue $className"
    val time = timeProcess(commandLine)
    println("""
        #  kernel time = ${time.kernel.toString(DurationUnit.SECONDS, 3)}
        #    user time = ${time.user.toString(DurationUnit.SECONDS, 3)}
        # sum cpu time = ${(time.user + time.kernel).toString(DurationUnit.SECONDS, 3)}
        #    real time = ${time.real.toString(DurationUnit.SECONDS, 3)}
        """.trimIndent()
    )
}