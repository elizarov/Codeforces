package archive.kh2

import kotlin.math.*

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = IntArray(n)
    val b = IntArray(n)
    val c = IntArray(n)
    for (i in 0 until n) {
        val l = readLine()!!.split(" ").map { it.toInt() }
        a[i] = l[0]
        b[i] = l[1]
        c[i] = l[2]
    }
    println(solveIceCream(a, b, c, n, k))
}

private fun solveIceCream(a: IntArray, b: IntArray, c: IntArray, n: Int, k: Int): Long {
    val sa = a.fold(0L, Long::plus)
    val sb = b.fold(0L, Long::plus)
    if (sa > k || sb < k) return -1
    var res = a.foldIndexed(0L) { i, acc, av -> acc + av.toLong() * c[i] }
    val e = c.mapIndexed { i, cv -> Extra(cv, b[i] - a[i]) }.sortedBy { it.c }
    var rem = k - sa
    var idx = 0
    while (rem > 0) {
        val take = min(rem, e[idx].q.toLong())
        rem -= take
        res += e[idx].c * take
        idx++
    }
    return res
}

data class Extra(val c: Int, val q: Int)