package archive.er68

import java.io.*

fun main() {
    val q = readLine()!!.toInt()
    bufferOut {
        repeat(q) {
            val ans = solveQuery()
            println(if (ans) "YES" else "NO")
        }
    }
}

private fun solveQuery(): Boolean {
    val s = readLine()!!
    val t = readLine()!!
    val p = readLine()!!
    val m = t.length
    val u = BooleanArray(m)
    var i = 0
    for (c in s) {
        while (i < m && c != t[i]) i++
        if (i >= m) return false
        u[i] = true
        i++
    }
    val a = IntArray(26)
    for (c in p) a[c - 'a']++
    for (j in 0 until m) if (!u[j]) {
        if (--a[t[j] - 'a'] < 0) return false
    }
    return true
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
