package archive.r613

import java.io.*
import kotlin.math.*

fun main() = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val es = ArrayList<Evt>(2 * n)
        for (i in 0 until n) {
            val (li, ri) = readLine()!!.splitToIntArray()
            es += Evt(1, li, i)
            es += Evt(-1, ri, i)
        }
        es.sort()
        var cur = 0
        var uc = 0
        var prev = Integer.MIN_VALUE
        val set = HashSet<Int>()
        val oc = IntArray(n) { -2 }
        val mc = HashMap<Int, Int>()
        for (e in es) {
            if (e.x > prev) {
                if (cur == 0) uc++
                if (cur == 1) oc[set.first()]++
            }
            if (e.d == 1) {
                cur++
                set += e.i
            }
            val old = mc[e.x] ?: 0
            if (cur > old) mc[e.x] = cur
            if (e.d == -1) {
                cur--
                set -= e.i
            }
            prev = e.x
        }
        for (e in es) {
            if (mc[e.x]!! > 1) oc[e.i]++
        }
        val best = max(-1, oc.max()!!)
        println(uc + best)
    }
}

class Evt(val d: Int, val x: Int, val i: Int) : Comparable<Evt> {
    override fun compareTo(other: Evt): Int {
        x.compareTo(other.x).let { if (it != 0) return it }
        return -d.compareTo(other.d)
    }
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