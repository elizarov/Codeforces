package archive.r528

import kotlin.math.*

fun main(args: Array<String>) {
    val p = MutableList(3) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        Pt(x, y)
    }
    p.sortBy { it.x }
    val (a, b, c) = p
    val q = ArrayList<Pt>()
    val y0 = min(a.y, c.y)
    val y1 = max(a.y, c.y)
    val yh = when {
        b.y >= y1 -> y1
        b.y <= y0 -> y0
        else -> b.y
    }
    for (x in a.x..c.x) q += Pt(x, yh)
    when {
        b.y > yh -> for (y in yh + 1..b.y) q += Pt(b.x, y)
        b.y < yh -> for (y in b.y until yh) q += Pt(b.x, y)
    }
    for (y in go(a.y, yh)) q += Pt(a.x, y)
    for (y in go(c.y, yh)) q += Pt(c.x, y)
    println(q.size)
    q.forEach { println("${it.x} ${it.y}") }
}

fun go(ys: Int, yh: Int): IntRange = when {
    ys < yh -> ys until yh
    ys > yh -> yh + 1..ys
    else -> IntRange.EMPTY
}

data class Pt(val x: Int, val y: Int)