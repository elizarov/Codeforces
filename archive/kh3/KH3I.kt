package archive.kh3

import java.util.*

private class Block(val l: Int, val r: Int) : Comparable<Block> {
    fun cover(c: Block) = l <= c.l && r >= c.r
    override fun compareTo(other: Block): Int = l.compareTo(other.l)
}

fun main() {
    val (n, d) = readLine()!!.split(" ").map { it.toInt() }
    val bh = arrayOfNulls<TreeSet<Block>>(n + 1) // blocks by height
    // Segment tree
    val tt = BooleanArray(4 * d) // terminal(leaf) node in segment tree
    val th = IntArray(4 * d) // max height in this segment
    tt[0] = true
    // Segment tree functions
    fun findMax(b: Block, i: Int, tl: Int, tr: Int): Int {
        if (tt[i] || b.l <= tl && b.r >= tr) return th[i]
        val tm = (tl + tr) / 2
        return maxOf(
            if (b.l <= tm) findMax(b, 2 * i + 1, tl, tm) else 0,
            if (b.r > tm) findMax(b, 2 * i + 2, tm + 1, tr) else 0
        )
    }
    fun setLeaf(i: Int, h: Int) {
        tt[i] = true
        th[i] = h
    }
    fun place(b: Block, h: Int, i: Int, tl: Int, tr: Int) {
        if (b.l <= tl && b.r >= tr) return setLeaf(i, h)
        val tm = (tl + tr) / 2
        val j1 = 2 * i + 1
        val j2 = 2 * i + 2
        if (tt[i]) { // split node
            tt[i] = false
            setLeaf(j1, th[i])
            setLeaf(j2, th[i])
        }
        if (b.l <= tm) place(b, h, j1, tl, tm)
        if (b.r > tm) place(b, h, j2, tm + 1, tr)
        th[i] = maxOf(th[j1], th[j2])
    }
    // Simulate each incoming block & print answer
    var bc = 0
    repeat(n) {
        val b = readLine()!!.split(" ").map { it.toInt() }.let{ (l, r) -> Block(l, r) }
        var maxH = findMax(b, 0, 1, d)
        while (true) {
            val bs = bh[maxH] ?: break
            var floor = bs.floor(b)
            if (floor != null && floor.r < b.l) floor = bs.higher(floor)
            if (floor == null) floor = bs.first()
            check(floor.l <= b.r)
            val list = bs.tailSet(floor).takeWhile { it.l <= b.r }
            if (!b.cover(list.first()) || !b.cover(list.last())) break
            for (c in list) bs -= c // don't use bs.removeAll(list)
            bc -= list.size
            maxH--
        }
        val h = maxH + 1
        place(b, h, 0, 1, d)
        val bs = bh[h] ?: run { TreeSet<Block>().also { bh[h] = it } }
        bs += b
        println(++bc)
    }
}
