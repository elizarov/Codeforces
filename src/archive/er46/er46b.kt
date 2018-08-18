package archive.er46

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.splitToIntArray()
    val a = "0 ${readLine()} $m".splitToIntArray()
    val b = IntArray(n + 5)
    val c = IntArray(n + 5)
    for (i in n downTo 0) {
        val d = a[i + 1] - a[i]
        var best = d + b[i + 2]
        if (d > 1) best = max(best, d - 1 + c[i + 1])
        if (i + 2 < a.size) {
            val d2 = a[i + 2] - a[i + 1]
            if (d2 > 1) best = max(best, d + d2 - 1 + c[i + 3])
        }
        b[i] = best
        c[i] = d + c[i + 2]
    }
    println(b[0])
}

private fun String.splitToIntArray(): IntArray {
    val n = length
    if (n == 0) return IntArray(0) // EMPTY
    var res = IntArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0
        var neg = false
        var c = get(i) // expecting number, IOOB if there is no number
        if (c == '-') {
            neg = true
            i++
            c = get(i) // expecting number, IOOB if there is no number
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d >= 0 && d <= 9) { "Unexpected character '$c' at $i" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $i" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $i" }
            i++
            if (i >= n) break
            c = get(i)
            if (c == ' ') break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m++] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $i" } }
        if (i >= n) break
        i++
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}