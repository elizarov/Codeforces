import algo.timeProcess
import java.io.File
import java.io.PrintStream
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    measureTime {
        val test = "00"
        System.setIn(File(test).inputStream())
        val out = PrintStream(File("$test.out"))
        val s: SolveE
        measureTime { s = SolveE()  }.also { println("input in ${it.toString(DurationUnit.SECONDS, 3)}") }
        measureTime { s.solve()     }.also { println("solve in ${it.toString(DurationUnit.SECONDS, 3)}") }
        measureTime { s.output(out) }.also { println("output in ${it.toString(DurationUnit.SECONDS, 3)}") }
    }.also { println("total in ${it.toString(DurationUnit.SECONDS, 3)}") }
}