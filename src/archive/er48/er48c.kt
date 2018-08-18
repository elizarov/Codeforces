package archive.er48

import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.splitToLongArray()
    val b = readLine()!!.splitToLongArray()
    val ap = Prep(a)
    val bp = Prep(b)
    var best = 0L
    var sum = 0L
    var t = 0
    for (i in 0 until n) {
        when (i % 2) {
            0 -> {
                best = max(best, sum + ap.sumInc(i, t) + bp.sumDec(i, t + n - i))
                sum += t * a[i] + (t + 1) * b[i]
            }
            1 -> {
                best = max(best, sum + bp.sumInc(i, t) + ap.sumDec(i, t + n - i))
                sum += t * b[i] + (t + 1) * a[i]
            }
        }
        t += 2
    }
    best = max(best, sum)
    println(best)
}

class Prep(a: LongArray) {
    val b = LongArray(a.size)
    val c = LongArray(a.size)
    val d = LongArray(a.size)

    init {
        var sb = 0L
        var sc = 0L
        var sd = 0L
        for (i in a.size - 1 downTo 0) {
            sb += a[i] * i
            b[i] = sb
            sc += a[i] * (a.size - i - 1)
            c[i] = sc
            sd += a[i]
            d[i] = sd
        }
    }

    fun sumInc(i: Int, t: Int): Long = b[i] + (t - i) * d[i]
    fun sumDec(i: Int, t: Int): Long = c[i] + t * d[i]
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