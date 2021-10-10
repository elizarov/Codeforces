package archive.r559

import java.io.*

fun main() = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    val (n, m) = readLine()!!.splitToIntArray()
    val b = readLine()!!.splitToIntArray()
    val g = readLine()!!.splitToIntArray()
    b.sort()
    val max = b[b.lastIndex]
    val max2 = b[b.lastIndex - 1]
    var sum = b.asSequence().map { it.toLong() }.sum().toBigInteger() * m.toBigInteger()
    var cnt = 0
    for (x in g) {
        if (max > x) {
            println(-1)
            return
        } else if (max < x) {
            cnt++
            sum += (x - max).toBigInteger()
        }
    }
    if (cnt == m) {
        sum += (max - max2).toBigInteger()
    }
    println(sum)
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