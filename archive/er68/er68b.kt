package archive.er68

import java.io.*
import kotlin.math.*

fun main() {
    val q = readLine()!!.toInt()
    bufferOut {
        repeat(q) { solveQuery() }
    }
}

private fun PrintWriter.solveQuery() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) { readLine()!!.map { it == '*' }.toBooleanArray() }
    val r = IntArray(n)
    val c = IntArray(m)
    for (i in 0 until n) for(j in 0 until m) {
        if (a[i][j]) {
            r[i]++
            c[j]++
        }
    }
    if (r.any { it == m } && c.any { it == n }) {
        println(0)
        return
    }
    var ans = Int.MAX_VALUE
    for (i in 0 until n) for(j in 0 until m) {
        var d = n + m - r[i] - c[j]
        if (!a[i][j]) d--
        ans = min(ans, d)
    }
    println(ans)
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
