package archive.kh4

import kotlin.math.*

fun main() {
    val (n, m, q) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
    val rs = LongArray(n) { i -> (0 until m).map { j -> a[i][j].toLong() }.sum() }.toDistSum()
    val cs = LongArray(m) { j -> (0 until n).map { i -> a[i][j].toLong() }.sum() }.toDistSum()
    fun ans() = rs.min()!! + cs.min()!!
    print(ans())
    repeat(q) {
        val (x1, y1, z) = readLine()!!.split(" ").map { it.toInt() }
        val (x, y) = (x1 - 1) to (y1 - 1)
        val d = (z - a[x][y]).toLong()
        for (i in 0 until n) rs[i] += abs(x - i) * d
        for (j in 0 until m) cs[j] += abs(y - j) * d
        a[x][y] = z
        print(" ${ans()}")
    }
}

private fun LongArray.toDistSum(): LongArray {
    val s = LongArray(size)
    var su = 0L
    var wsu = 0L
    var sd = 0L
    var wsd = 0L
    for (i in 0 until size) {
        sd += get(i)
        wsd += (i + 1) * get(i)
    }
    for (i in 0 until size) {
        wsd -= sd
        sd -= get(i)
        s[i] = wsu + wsd
        su += get(i)
        wsu += su
    }
    return s
}