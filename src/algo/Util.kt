package algo

import com.sun.jna.platform.win32.Kernel32
import com.sun.jna.platform.win32.WinBase
import com.sun.jna.platform.win32.WinNT
import kotlin.time.*

class ProcessTimes(
    val kernel: Duration,
    val user: Duration
)

fun WinBase.FILETIME.toDuration(): Duration {
    val value = (dwHighDateTime.toULong() shl 32) + dwLowDateTime.toUInt()
    return (value.toLong() * 100).toDuration(DurationUnit.NANOSECONDS)
}

fun getProcessTimes(): ProcessTimes {
    val kernel32: Kernel32 = Kernel32.INSTANCE
    val currentProcess: WinNT.HANDLE = kernel32.GetCurrentProcess()
    val create = WinBase.FILETIME()
    val exit = WinBase.FILETIME()
    val kernel = WinBase.FILETIME()
    val user = WinBase.FILETIME()
    check(kernel32.GetProcessTimes(currentProcess, create, exit, kernel, user))
    return ProcessTimes(kernel = kernel.toDuration(), user = user.toDuration())
}

fun measureCpuTime(block: () -> Unit): ProcessTimes {
    val start = getProcessTimes()
    block()
    val stop = getProcessTimes()
    return ProcessTimes(kernel = stop.kernel - start.kernel, user = stop.user - start.user)
}

@OptIn(ExperimentalTime::class)
fun time(block: () -> Unit) {
    val time = measureTimedValue { measureCpuTime(block) }
    println("""
        #   real algo.time = ${time.duration.toString(DurationUnit.SECONDS, 3)}
        # kernel algo.time = ${time.value.kernel.toString(DurationUnit.SECONDS, 3)}
        #   user algo.time = ${time.value.user.toString(DurationUnit.SECONDS, 3)}
        #    cpu algo.time = ${(time.value.user + time.value.kernel).toString(DurationUnit.SECONDS, 3)}
        """.trimIndent()
    )
}