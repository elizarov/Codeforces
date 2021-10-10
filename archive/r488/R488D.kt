package archive.r488

import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.splitToIntArray()
    val b = readLine()!!.splitToIntArray()
    a.zip(b).sortedBy { -it.first }.forEachIndexed { i, (ai, bi) ->
        a[i] = ai
        b[i] = bi
    }
    val h = (n + 1) / 2
    var lo = 0L // blows up
    var hi = 10000_0000_000L // works ~ 10^11
    val d = LongArray(n)
    val dp = Array(n + 1) { Array(n + 1) { LongArray(n + 1) } }
    while (lo < hi - 1) {
        val x = (lo + hi) / 2
        for (i in 0 until n) d[i] = b[i] * x - 1000L * a[i]
        for (i in 0 until n) {
            for (j in 0..i + 1) {
                for (k in 0..i + 1 - j)
                    dp[i + 1][j][k] = Long.MIN_VALUE
            }
            for (j in 0..i) { // # of assigned power > a[i-1]
                for (k in 0..i - j) { // # of assigned power == a[i-1]
                    val c = dp[i][j][k]
                    if (c == Long.MIN_VALUE) continue
                    var nj = j
                    var nk = k
                    if (i == 0 || a[i - 1] != a[i]) { // diff
                        nj = j + k
                        nk = 0
                    }
                    dp[i + 1][nj][nk + 1] = max(dp[i + 1][nj][nk + 1], c + d[i]) // assign 1st
                    if (nj > 0) dp[i + 1][nj - 1][nk] = max(dp[i + 1][nj - 1][nk], c) // assign 2nd
                }
            }
        }
        var best = Long.MIN_VALUE
        for (j in 0..n) {
            for (k in 0..n - j) {
                best = max(best, dp[n][j][k])
            }
        }
        if (best >= 0) {
            hi = x
        } else {
            lo = x
        }
    }
    println(hi)
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