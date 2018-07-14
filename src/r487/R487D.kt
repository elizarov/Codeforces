package r487

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, l, wmax) = readLine()!!.split(" ").map { it.toInt() }
    val xia = ArrayList<Int>(n)
    val xja = ArrayList<Int>(n)
    for (i in 0 until n) {
        val (x, v) = readLine()!!.split(" ").map { it.toInt() }
        when (v) {
            1 -> xia += x
            -1 -> xja += x
            else -> error("!")
        }
    }
    xja.sort()
    var cnt = 0L
    xil@ for (xii in xia) {
        val xi = xii.toLong()
        val lo1 = roundLo(((-l.toLong() * (1 - wmax)) - xi * (1 + wmax)).toDouble() / (1 - wmax))
        val lo2 = roundLo(((-l.toLong() * (1 + wmax)) - xi * (1 - wmax)).toDouble() / (1 + wmax))
        if (wmax == 1) {
            if (xi * (1 + wmax) + l.toLong() * (1 - wmax) >= 0) continue@xil
        }
        val lo = maxOf(xii, lo1, lo2)
        var loi = xja.binarySearch(lo)
        if (loi < 0) loi = -loi - 1
        cnt += xja.size - loi
    }
    println(cnt)
}

fun roundHi(d: Double): Int {
    if (d.isNaN()) return Int.MAX_VALUE
    val i = d.toInt()
    if (d == i.toDouble()) return (d - 1).toInt()
    return floor(d).toInt()
}

fun roundLo(d: Double): Int {
    if (d.isNaN() || d.isInfinite()) return Int.MIN_VALUE
    val i = d.toInt()
    if (d == i.toDouble()) return (d + 1).toInt()
    return ceil(d).toInt()
}

/*

2 1 100
-10 1
10 -1

== 1

2 10 1
-20 1
10 -1

== 1


 */
