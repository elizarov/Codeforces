package archive.r530

import kotlin.math.*

fun main() {
    val n = readLine()!!.toInt()
    val p = readLine()!!.splitToIntArray()
    val s = readLine()!!.splitToIntArray()
    println(SumTree(n, p, s).solve())
}

class SumTree(val n: Int, val p: IntArray, val s: IntArray) {
    val c = Array(n) { ArrayList<Int>() }
    val a = IntArray(n)

    fun solve(): Long {
        for (i in 1 until n) {
            c[p[i - 1] - 1].add(i)
        }
        for (j in 1 until n) {
            if (s[j] != -1) continue
            val i = p[j - 1] - 1
            var min = Int.MAX_VALUE
            for (k in c[j]) {
                min = min(min, s[k])
            }
            if (min < s[i]) return -1L
            s[j] = if (min == Int.MAX_VALUE) s[i] else min
        }
        for (j in 1 until n) {
            val i = p[j - 1] - 1
            a[j] = s[j] - s[i]
        }
        a[0] = s[0]
        return a.map { it.toLong() }.sum()
    }
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
            require(d in 0..9) { "Unexpected character '$c' at $i" }
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