package archive.er56

import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val b = readLine()!!.splitToLongArray()
    val a = LongArray(n)
    a[0] = 0
    a[n - 1] = b[0]
    for (i in 1 until n / 2) {
        val j = n - 1 - i
        a[j] = min(a[j + 1], b[i] - a[i - 1])
        a[i] = b[i] - a[j]
    }
    println(a.joinToString(" "))
}

private fun String.splitToLongArray(): LongArray {
    val n = length
    if (n == 0) return LongArray(0) // EMPTY
    var res = LongArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0L
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
            require(cur >= Long.MIN_VALUE / 10) { "Overflow at $i" }
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