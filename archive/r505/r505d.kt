package archive.r505

import java.util.*

private tailrec fun gcd(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcd(b, a % b)
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.splitToIntArray()
    println(if (recoverBst(n, a)) "Yes" else "No")
}

fun recoverBst(n: Int, a: IntArray): Boolean {
    val d = Array(n + 1) { arrayOfNulls<S>(n) }
    fun possible(r: Int, i: Int, j: Int): Boolean {
        if (j - i == 0) return true
        if (j - i == 1) {
            return r == n || gcd(a[r], a[i]) > 1
        }
        val s = d[r][i] ?: S(n).also { d[r][i] = it }
        if (s.known[j]) return s.ans[j]
        s.known.set(j)
        for (k in i until j) {
            if (r == n || gcd(a[r], a[k]) > 1) {
                if (possible(k, i, k) && possible(k, k + 1, j)) {
                    s.ans.set(j)
                    return true
                }
            }
        }
        return false
    }

    val ans = possible(n, 0, n)
    return ans
}

class S(n: Int) {
    val known = BitSet(n + 1)
    val ans = BitSet(n + 1)
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