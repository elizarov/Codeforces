import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    val t = 300
    val n = t * t + 1
    val a = IntArray(n) { 1 }
    val p = IntArray(n)
    for (i in 0 until t) for (j in 0 until t) {
        p[i * t + j + 1] = if (j == 0) 0 else i * t + j
    }
    val qs = Array(t * t) {
        val a = it / t
        val b = it % t
        Q(a * t + t, b * t + t)
    }
    val time = measureTime {
        solveE(a, p, qs)
    }
    println(time.toString(DurationUnit.SECONDS, 3))
}