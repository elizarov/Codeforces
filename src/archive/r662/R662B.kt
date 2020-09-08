package archive.r662

import kotlin.math.*

fun main() = System.`in`.bufferedReader().run {
    val n = readLine()!!.toInt()
    val c = IntArray(100_001)
    val d = IntArray(9)
    fun event(x: Int, z: Int) {
        val prev = c[x].coerceAtMost(8)
        d[prev]--
        c[x] += z
        val cur = c[x].coerceAtMost(8)
        d[cur]++
    }
    for (x in readLine()!!.split(" ").map { it.toInt() }) {
        event(x, 1)
    }
    fun query(): Boolean {
        if (d[8] > 0) return true
        val d6 = d[6] + d[7]
        val d4 = d[4] + d[5] + d6
        val d2 = d[2] + d[3] + d4
        if (d6 > 0 && d2 > 1) return true
        if (d4 > 0 && d2 > 2) return true
        if (d4 > 1) return true
        return false
    }
    val ans = Array(readLine()!!.toInt()) {
        val s = readLine()!!.split(" ")
        val x = s[1].toInt()
        when (s[0]) {
            "+" -> event(x, 1)
            "-" -> event(x, -1)
        }
        query()
    }
    println(ans.joinToString("\n") { if (it) "YES" else "NO" })
}