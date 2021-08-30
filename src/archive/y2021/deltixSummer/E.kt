package archive.y2021.deltixSummer

import kotlin.math.*

fun main() {
    val (n , q) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.split(" ").map { it.toInt() }
    val b = readLine()!!.split(" ").map { it.toInt() }
    val c = IntArray(n) { i -> b[i] - a[i] }
    val ft = LFenwickTree(n)
    for (i in 0 until n) {
        ft.update(i, c[i].toLong())
    }
    val st = SegmentTree(c)
    val ans = LongArray(q) {
        val (l , r) = readLine()!!.split(" ").map { it.toInt() - 1 }
        if (ft.sum(l, r) != 0L) {
            -1L
        } else {
            st.get(l, r).ans
        }
    }
    println(ans.joinToString("\n"))
}

class LFenwickTree(n: Int) {
    val a = LongArray(n)

    fun fill(v: Long) {
        for (i in 0 until a.size) {
            val f = i and (i + 1)
            a[i] = (i - f + 1) * v
        }
    }

    fun sum(toIndex: Int): Long { // inclusive
        var i = toIndex
        var sum = 0L
        while (i >= 0) {
            sum += a[i]
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun sum(fromIndex: Int, toIndex: Int): Long = // inclusive
        if (toIndex < fromIndex) 0 else
            sum(toIndex) - sum(fromIndex - 1)

    fun update(index: Int, delta: Long) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}

private tailrec fun approxSegmentTreeSize(p: Int, tl: Int, tr: Int): Int {
    if (tl >= tr) return p + 1
    return approxSegmentTreeSize(2 * p + 2, (tl + tr + 1) / 2, tr)
}

class Ans(val ls: Long, val rs: Long, val sc: Int, val ans: Long)

val ans0 = Ans(0L, 0L, 0,0L)

fun one(c: Int) = Ans(c.toLong(), c.toLong(), 1, c.absoluteValue.toLong())

fun merge(a: Ans, b: Ans): Ans {
    var ans = maxOf(a.ans, b.ans)
    if (a.rs.sign * b.ls.sign >= 0) {
        val mid = a.rs + b.ls
        ans = maxOf(ans, mid.absoluteValue)
        val ls = if (a.sc <= 1) mid else a.ls
        val rs = if (b.sc <= 1) mid else b.rs
        val d = if (a.sc >= 1 && b.sc >= 1) 1 else 0
        return Ans(ls, rs, a.sc + b.sc - d, ans)
    }
    return Ans(a.ls, b.rs, a.sc + b.sc, ans)
}

class SegmentTree(
    a: IntArray,
) {
    private val n = a.size
    private val t = arrayOfNulls<Ans>(approxSegmentTreeSize(0, 0, n - 1))

    init {
        build(a, 0, 0, n - 1)
    }

    private fun build(a: IntArray, p: Int, tl: Int, tr: Int): Ans {
        val x = if (tl == tr) {
            one(a[tl])
        } else {
            val m = (tl + tr) / 2
            merge(
                build(a, 2 * p + 1, tl, m),
                build(a, 2 * p + 2, m + 1, tr)
            )
        }
        t[p] = x
        return x
    }

    operator fun get(l: Int, r: Int): Ans = get0(0, 0, n - 1, l, r)

    private fun get0(p: Int, tl: Int, tr: Int, l: Int, r: Int): Ans {
        if (l > r) return ans0
        if (l == tl && r == tr) return t[p]!!
        val m = (tl + tr) / 2
        return merge(
            get0(2 * p + 1, tl, m, l, min(r, m)),
            get0(2 * p + 2, m + 1, tr, max(l, m + 1), r)
        )
    }
}