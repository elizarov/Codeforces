import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    val n = 50000
    val m = 10
    val rnd = Random(1)
    val a = Array(n) { IntArray(m) { it } }
    val time = measureTime {
        val ans = solveD(n, m, a)
        check(ans.all { it == m })
    }
    println(time.toString(DurationUnit.SECONDS, 3))
}