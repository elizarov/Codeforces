package archive.r538

import kotlin.math.*

fun main() {
    val (n, b) = readLine()!!.split(' ').map { it.toLong() }
    var ans = Long.MAX_VALUE
    factor(b) { p, k ->
        ans = min(ans, pp(n, p) / k)
    }
    println(ans)
}

fun factor(x: Long, op: (p: Long, k: Int) -> Unit) {
    var rem = x
    var p = 2L
    while (p * p <= rem) {
        var k = 0
        while (rem % p == 0L) {
            rem /= p
            k++
        }
        if (k > 0) op(p, k)
        p++
    }
    if (rem > 1) op(rem, 1)
}

fun pp(n: Long, p: Long): Long {
    var ans = 0L
    var cur = p
    while (cur <= n) {
        ans += n / cur
        if (cur > (n + p - 1) / p) break
        cur *= p
    }
    return ans
}