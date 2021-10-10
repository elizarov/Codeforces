package archive.r504

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toLong() }
    println(countWays(n, k))
}

fun countWays(n: Long, k: Long): Long {
    if (k < 3 || k > 2 * n - 1) return 0
    var m1 = k / 2
    var m2 = k - m1
    if (m1 == m2) {
        m1--
        m2++
    }
    return min(n - m2 + 1, m1)
}
