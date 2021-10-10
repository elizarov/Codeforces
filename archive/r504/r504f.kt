package archive.r504

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, k, m) = readLine()!!.splitToIntArray()
    val g = Array(n + 1) { ArrayList<G>() }
    val cl = List(k) {
        val (a, b) = readLine()!!.splitToIntArray()
        val c = C()
        g[a].add(G(a, b, c))
        g[b].add(G(b, a, c))
        c
    }
    val el = List(m) {
        val (a, b, w) = readLine()!!.splitToIntArray()
        E(a, b, w)
    }
    val c = IntArray(n + 1)
    val h = IntArray(n + 1)
    val prev = arrayOfNulls<G>(n + 1)
    var cc = 0
    fun dfs(i: Int, hi: Int, p: G?) {
        c[i] = cc
        h[i] = hi
        prev[i] = p
        for (e in g[i]) {
            if (c[e.b] == 0) dfs(e.b, hi + 1, e)
        }
    }
    for (i in 1..n) {
        if (c[i] == 0 && g[i].isNotEmpty()) {
            cc++
            dfs(i, 0, null)
        }
    }
    for ((a, b, cost) in el) {
        if (c[a] == c[b] && c[a] != 0) {
            var ca = a
            var cb = b
            var ha = h[a]
            var hb = h[b]
            while (ca != cb) {
                var e: G
                if (ha > hb) {
                    e = prev[ca]!!
                    ca = e.a
                    ha--
                } else {
                    e = prev[cb]!!
                    cb = e.a
                    hb--
                }
                e.c.c = min(e.c.c, cost)
            }
        }
    }
    if (cl.any { it.c == Int.MAX_VALUE }) {
        println(-1)
    } else {
        println(cl.map { it.c.toLong() }.sum())
    }
}

class C(var c: Int = Int.MAX_VALUE)
data class G(val a: Int, val b: Int, val c: C)
data class E(val a: Int, val b: Int, val c: Int)

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