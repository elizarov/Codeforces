package archive.r626

import java.io.*

fun main() = println(System.`in`.bufferedReader().solve().joinToString("\n"))

private fun BufferedReader.solve() = Array(readLine()!!.toInt()) { tn ->
    if (tn > 0) readLine()
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val c = readLine()!!.split(" ").map { it.toLong() }
    val g = Array(n) { ArrayList<Int>(2) }
    repeat(m) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[v].add(u)
    }
    val sum = HashMap<Long, Long>()
    for ((i, l) in g.withIndex()) {
        if (l.isEmpty()) continue
        l.sort()
        val h = l.fold(1L) { a, x -> a * 1299827 + x }
        sum[h] = (sum[h] ?: 0) + c[i]
    }
    sum.values.fold(0L) { a, x -> gcd(a, x) }
}

private tailrec fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)