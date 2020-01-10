package archive.r613

import java.io.*
import kotlin.math.*

fun main() = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.splitToIntArray()
        var s = 0L
        var minS = 0L
        var maxR = Long.MIN_VALUE
        for (i in 0 until n - 1) {
            s += a[i]
            maxR = max(maxR, s - minS)
            minS = min(minS, s)
        }
        s += a[n - 1]
        var ls = 0L
        for (i in n - 1 downTo 1) {
            ls += a[i]
            maxR = max(maxR, ls)
        }
        println(if (s > maxR) "YES" else "NO")
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