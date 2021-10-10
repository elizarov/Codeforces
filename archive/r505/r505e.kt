package archive.r505

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val x0 = IntArray(m)
    val x1 = IntArray(m)
    val y0 = IntArray(m)
    val y1 = IntArray(m)
    repeat(m) { i ->
        val (xi, yi) = readLine()!!.split(" ").map { it.toInt() }
        x0[i] = xi
        y0[i] = yi
    }
    repeat(m) { i ->
        val (xi, yi) = readLine()!!.split(" ").map { it.toInt() }
        x1[i] = xi
        y1[i] = yi
    }
    if (n == 1) {
        println(0)
        return
    }
    val c = Array(n + 1) { IntArray(n + 1) { -1 } }
    val d = Array(n + 1) { IntArray(n + 1) { -1 } }
    for (i in 0 until m) {
        c[x0[i]][y0[i]] = i
        d[x1[i]][y1[i]] = i
    }
    val moves = ArrayList<Move>()

    fun MutableList<Move>.straight(x0: Int, y0: Int, x1: Int, y1: Int) {
        val k = c[x0][y0]
        check(k >= 0)
        val dx = (x1 - x0).sign
        val dy = (y1 - y0).sign
        var x = x0
        var y = y0
        while (x != x1 || y != y1) {
            add(Move(x, y, x + dx, y + dy))
            x += dx
            y += dy
            check(c[x][y] < 0)
        }
        c[x0][y0] = -1
        c[x1][y1] = k
    }

    var ly1 = 1
    var ry1 = n
    var ly2 = 1
    var ry2 = n
    while (ly2 <= ry2) {
        while (ly2 <= ry2 && c[2][ly2] < 0) ly2++
        while (ly2 <= ry2 && c[2][ry2] < 0) ry2--
        if (ly2 > ry2) break
        while (c[1][ly1] >= 0) ly1++
        while (c[1][ry1] >= 0) ry1--
        when {
            ly1 <= ly2 -> {
                moves.straight(2, ly2, 2, ly1)
                moves.straight(2, ly1, 1, ly1)
                ly1++
                ly2++
            }
            ry1 >= ry2 -> {
                moves.straight(2, ry2, 2, ry1)
                moves.straight(2, ry1, 1, ry1)
                ry1--
                ry2--
            }
            else -> error("!!!")
        }
    }
    for (x in 3..n) {
        for (y in 1..n) {
            val k = c[x][y]
            if (k >= 0) {
                while (c[1][ly1] >= 0) ly1++
                moves.straight(x, y, 2, y)
                moves.straight(2, y, 2, ly1)
                moves.straight(2, ly1, 1, ly1)
                ly1++
            }
        }
    }
    val py = IntArray(m) { -1 }
    for (y in 1..n) {
        if (c[1][y] >= 0) {
            py[c[1][y]] = y
        }
    }
    for (x in n downTo 3) {
        for (y in 1..n) {
            val k = d[x][y]
            if (k >= 0) {
                val py1 = py[k]
                moves.straight(1, py1, 2, py1)
                moves.straight(2, py1, 2, y)
                moves.straight(2, y, x, y)
                py[k] = -1
            }
        }
    }
    val ord = ArrayList<Int>()
    for (y in n downTo 1) {
        val k = d[2][y]
        if (k >= 0) ord.add(k)
    }
    for (y in 1..n) {
        val k = d[1][y]
        if (k >= 0) ord.add(k)
    }
    for (y in 1..n) {
        val k = c[1][y]
        if (k >= 0) {
            moves.straight(1, y, 2, y)
            check(py[k] == y)
        }
    }
    for ((index, k) in ord.withIndex()) {
        val fy = py[k]
        val ty = index + 1
        moves.straight(2, fy, 1, fy)
        moves.straight(1, fy, 1, ty)
        py[k] = ty
    }
    for (y in n downTo 1) {
        val k = d[2][y]
        if (k >= 0) {
            val fy = py[k]
            moves.straight(1, fy, 1, 1)
            moves.straight(1, 1, 2, 1)
            moves.straight(2, 1, 2, y)
        }
    }
    ly1 = 1
    ry1 = n
    ly2 = 1
    ry2 = n
    while (ly1 <= ry1) {
        while (ly1 <= ry1 && c[1][ly1] < 0) ly1++
        while (ly1 <= ry1 && c[1][ry1] < 0) ry1--
        if (ly1 > ry1) break
        while (ly2 <= ry2 && d[1][ly2] < 0) ly2++
        while (ly2 <= ry2 && d[1][ry2] < 0) ry2--
        when {
            ly2 <= ly1 -> {
                moves.straight(1, ly1, 1, ly2)
                ly1++
                ly2++
            }
            ry2 >= ry1 -> {
                moves.straight(1, ry1, 1, ry2)
                ry1--
                ry2--
            }
            else -> error("???")
        }
    }

    println(moves.size)
    println(moves.joinToString("\n"))
}

class Move(val x0: Int, val y0: Int, val x1: Int, val y1: Int) {
    override fun toString(): String = "$x0 $y0 $x1 $y1"
}

