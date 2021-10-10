package archive.unsorted1

import kotlin.system.*

fun main() {
    val n = 200_000
    val m = 60
    val p = 15
    val b = LongArray(n)
    val mask = (1L shl p) - 1
    for (i in 0 until n) {
        b[i] = mask
    }
    val time = measureTimeMillis {
        val ans = solveD(n, m, b)
        check(ans == mask)
    }
    println("time = $time ms")
}