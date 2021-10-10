package archive.r517

import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array<CharArray>(n) { readLine()!!.toCharArray() }
    val o = Array<IntArray>(n) { IntArray(n) }
    val pa = Array<P>(n) { P() }
    for (d in 2 * n - 3 downTo k) {
        var cnt = 0
        for (x in max(0, d - n + 1)..min(d, n - 1)) {
            val y = d - x
            val s = step(o, x, y, n)
            val p = pa[cnt++]
            p.x = x
            p.y = y
            p.c = a[x][y]
            p.s = s
        }
        pa.sort(0, cnt)
        var ord = 0
        for (i in 0 until cnt) {
            val c = pa[i]
            if (i == 0 || c > pa[i - 1]) ord++
            o[c.x][c.y] = ord
        }
    }
    val res = StringBuilder()
    repeat(k) { res.append('a') }
    var x = pa[0].x
    var y = pa[0].y
    while (true) {
        res.append(a[x][y])
        if (x == n - 1 && y == n - 1) break
        val s = step(o, x, y, n)
        if (x < n - 1 && s == o[x + 1][y]) x++ else y++
    }
    println(res)
}

private fun step(o: Array<IntArray>, x: Int, y: Int, n: Int): Int {
    var s = Int.MAX_VALUE
    if (x < n - 1) s = o[x + 1][y]
    if (y < n - 1 && o[x][y + 1] < s) s = o[x][y + 1]
    return s
}


class P : Comparable<P> {
    var x = 0
    var y = 0
    var c = ' '
    var s = 0

    override fun compareTo(other: P): Int {
        if (c > other.c) return 1
        if (c < other.c) return -1
        return s - other.s
    }
}
