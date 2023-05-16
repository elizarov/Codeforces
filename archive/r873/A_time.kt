import java.io.*
import kotlin.time.*

@OptIn(ExperimentalTime::class)
fun main() {
    System.setIn(File("input.txt").inputStream().buffered())
    val time = measureTime {
        runA()
    }
    println("Done in ${time.toString(DurationUnit.SECONDS, 3)}")
}