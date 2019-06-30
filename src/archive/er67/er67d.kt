package archive.er67

import java.io.*

fun main() {
    val t = readLine()!!.toInt()
    bufferOut {
        repeat(t) {
            solveQuery()
        }
    }
}

fun PrintWriter.solveQuery() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.splitToIntArray()
    val b = readLine()!!.splitToIntArray()
    val c = IntArray(n + 1)
    var nz = 0

    fun adj(k: Int, d: Int) {
        if (c[k] == 0) nz++
        c[k] += d
        if (c[k] == 0) nz--
    }
    
    var i = 0
    while (i < n) {
        while (i < n && a[i] == b[i]) i++
        if (i >= n) break
        var j = i
        while (j < n) {
            adj(a[j], 1)
            adj(b[j], -1)
            if (nz == 0) {
                
            }
            j++

        }
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