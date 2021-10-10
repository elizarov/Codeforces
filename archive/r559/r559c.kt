package archive.r559

import java.io.*
import kotlin.math.*

fun main() = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    val t = readLine()!!.toInt()
    repeat(t) { solveCase() }
}

private val MAX_N = 500_000
private val bf = IntArray(MAX_N + 2)
private val bn = IntArray(MAX_N + 2)
private val a = IntArray(MAX_N + 1)
private val t = IntArray(4 * MAX_N + 1)

private fun build(a: IntArray, p: Int, tl: Int, tr: Int): Int {
    val x = if (tl == tr) {
        a[tl]
    } else {
        val m = (tl + tr) / 2
        max(
            build(a, 2 * p + 1, tl, m),
            build(a, 2 * p + 2, m + 1, tr)
        )
    }
    t[p] = x
    return x
}

private fun get(l: Int, r: Int, n: Int): Int = get0(0, 0, n - 1, l, r)

private fun get0(p: Int, tl: Int, tr: Int, l: Int, r: Int): Int {
    if (l > r) return -1
    if (l == tl && r == tr) return t[p]
    val m = (tl + tr) / 2
    return max(
        get0(2 * p + 1, tl, m, l, min(r, m)),
        get0(2 * p + 2, m + 1, tr, max(l, m + 1), r)
    )
}

private fun PrintWriter.solveCase() {
    val n = readLine()!!.toInt()
    val next = readLine()!!.splitToIntArray()
    bf.fill(0, 0, n + 2)
    a.fill(0, 0, n + 1)
    build(next, 0, 0, n - 1)
    for (i in n downTo 1) {
        val nn = next[i - 1]
        if (nn < 0) continue
        bn[i] = bf[nn]
        bf[nn] = i
    }
    var cur = n
    for (nn in n + 1 downTo 1) {
        var p = bf[nn]
        if (p == 0) continue
        if (nn <= n && a[nn] == 0) a[nn] = cur--
        while (p > 0) {
            val q = bn[p]
            a[p] = cur--
            val l = p + 1
            val r = if (q > 0) q - 1 else nn - 1
            val g = get(l - 1, r - 1, n)
            if (g >= r) {
                println(-1)
                return
            }
            p = q
        }
    }
    for (i in 1..n) {
        if (a[i] == 0)
            a[i] = cur--
    }
    println(a.asSequence().drop(1).take(n).joinToString(" "))
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }

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