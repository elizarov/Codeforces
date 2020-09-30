package archive.gf1

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) { readLine()!!.split(" ").map { it.toInt() }.let { (x, y) -> Pt(x, y) }}
    val c0 = Array(m) { readLine()!!.split(" ").map { it.toInt() }.let { (x, y) -> Pt(x, y) }}
        .sortedByDescending { it.x }
        .sortedByDescending { it.y }
    var prev = Pt(-1, MAX)
    val c = ArrayList<Pt>()
    for (pt in c0) {
        if (pt.x > prev.x && pt.y < prev.y) {
            c.add(pt)
            prev = pt
        }
    }
    c.add(0, Pt(-100, c[0].y))
    c.add(Pt(c.last().x, -100))
    val ss = ArrayList<Seg>()
    for (g in a) {
        prev = c[0]
        var f = false
        for (i in 1 until c.size) {
            val pt = c[i]
            if (pt.x >= g.x && pt.y >= g.y) {
                f = true
                val dx1 = (prev.x - g.x + 1).coerceAtLeast(0)
                val dx2 = pt.x - g.x
                val dy2 = pt.y - g.y + 1
                ss += Seg(dy2, dx1, dx2)
            }
            if (pt.y < g.y) {
                if (f) {
                    val dx = (prev.x - g.x + 1).coerceAtLeast(0)
                    ss += Seg(0, dx, MAX)
                }
                break
            }
            prev = pt
        }
    }
    ss += Seg(MAX, MAX, MAX)
    val d = TreeMap<Int, Int>()
    ss.sortBy { it.dx1 }
    var dx = 0
    var ans = MAX
    for (s in ss) {
        if (s.dx1 > dx) {
            ans = minOf(ans, (d.firstEntry()?.value ?: 0) + dx)
            dx = s.dx1
            while (d.isNotEmpty() && d.firstKey() < dx) d.remove(d.firstKey())
        }
        val tm = d.tailMap(s.dx2, true)
        if (tm.isNotEmpty() && tm.firstEntry().value >= s.c) continue
        val hm = d.headMap(s.dx2, true)
        while (hm.isNotEmpty() && hm.lastEntry().value <= s.c) hm.remove(hm.lastKey())
        d[s.dx2] = s.c
    }
    println(ans)
}

data class Pt(val x: Int, val y: Int)

data class Seg(val c: Int, val dx1: Int, val dx2: Int) // cost = c + dx

const val MAX = 1_000_001