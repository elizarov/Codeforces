import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    val max = 1000000000
    val n = max
    val ps = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    var m1 = 1
    var m2 = 1
    var i = 0
    while (m1.toLong() * ps[i] <= max) m1 *= ps[i++]
    while (m1.toLong() * 2 <= max) m1 *= 2
    while (m2.toLong() * ps[i] <= max) m2 *= ps[i++]
    println("$n $m1 $m2")
    val time = measureTime {
        repeat(10) {
            solveE(n, m1, m2)
        }
    }
    println(time.toString(DurationUnit.SECONDS, 3))

}