package archive.r491

import kotlin.math.min

fun main(args: Array<String>) {
    val n = readLine()!!.toLong()
    var lo = 0L
    var hi = n
    while (lo < hi - 1) {
        val k = (lo + hi) / 2
        var cur = n
        var sum = 0L
        while (cur > 0) {
            val v = min(k, cur)
            sum += v
            cur -= v
            val p = cur / 10
            cur -= p
        }
        if (2 * sum >= n) {
            hi = k
        } else {
            lo = k
        }
    }
    println(hi)
}