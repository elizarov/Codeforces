package archive.er56

import java.io.*
import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.splitToIntArray()
    val b = readLine()!!.splitToIntArray()
    with(PermIntersect(n, a, b)) {
        bufferOut { process(m) }
    }
}

class PermIntersect(
    val n: Int,
    val a: IntArray,
    val b: IntArray
) {
    val aa = IntArray(n + 1)
    val bb = IntArray(n + 1)

    init {
        for (i in 0 until n) {
            aa[a[i]] = i
            bb[b[i]] = i
        }
    }

    val k = sqrt(n.toDouble()).roundToInt()
    val d = IntArray(k + 1) { i -> i * n / k }

    val f = IFenwickTree2(k, k)

    fun count(u: Int, v: Int): Int {
        val l = d[v]
        val r = d[v + 1]
        var cnt = 0
        for (i in d[u] until d[u + 1])
            if (bb[a[i]] in l until r)
                cnt++
        return cnt
    }

    init {
        for (x in 0 until k) for (y in 0 until k) f.update(x, y, count(x, y))
    }

    fun doQuery(la: Int, ra: Int, lb: Int, rb: Int): Int {
        val al = d.left(la)
        val ar = d.right(ra)
        val bl = d.left(lb)
        val br = d.right(rb)
        var cnt = f.sum(al, bl, ar, br)
        val na = if (al <= ar) d[al] - 1 else ra
        for (i in la..na)
            if (bb[a[i]] in lb..rb) cnt++
        if (al <= ar) for (i in max(d[ar + 1], la)..ra)
            if (bb[a[i]] in lb..rb) cnt++
        val nb = if (bl <= br) d[bl] - 1 else rb
        for (j in lb..nb)
            if (aa[b[j]] in d[al] until d[ar + 1]) cnt++
        if (bl <= br) for (j in max(d[br + 1], lb)..rb)
            if (aa[b[j]] in d[al] until d[ar + 1]) cnt++
        return cnt
    }

    fun doUpdate(u: Int, v: Int) {
        val uy = d.affected(u)
        val vy = d.affected(v)
        val bu = b[v]
        val bv = b[u]
        b[u] = bu
        b[v] = bv
        bb[bu] = u
        bb[bv] = v
        if (uy != vy) {
            val ux = d.affected(aa[bu])
            val vx = d.affected(aa[bv])
            f.update(ux, uy, 1)
            f.update(vx, uy, -1)
            f.update(vx, vy, 1)
            f.update(ux, vy, -1)
        }
    }

    fun PrintWriter.process(m: Int) {
        repeat(m) {
            val s = readLine()!!.split(" ").map { it.toInt() }
            when (s[0]) {
                1 -> {
                    val (la, ra, lb, rb) = s.subList(1, 5).map { it - 1 }
                    println(doQuery(la, ra, lb, rb))
                }
                2 -> {
                    val (x, y) = s.subList(1, 3).map { it - 1 }
                    doUpdate(x, y)
                }
            }
        }
    }
}

private fun IntArray.affected(x: Int): Int {
    val i = binarySearch(x)
    return if (i >= 0) i else -i - 2
}

private fun IntArray.left(x: Int): Int {
    val i = binarySearch(x)
    return if (i >= 0) i else -i - 1
}

private fun IntArray.right(x: Int): Int {
    val i = binarySearch(x)
    return if (i >= 0) i - 1 else -i - 3
}

private fun String.splitToIntArray(): IntArray {
    val n = length
    if (n == 0) return IntArray(0) // EMPTY
    var res = IntArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0
        var neg = false
        var c = get(i) // expecting number, IOOB if there is no number
        if (c == '-') {
            neg = true
            i++
            c = get(i) // expecting number, IOOB if there is no number
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d >= 0 && d <= 9) { "Unexpected character '$c' at $i" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $i" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $i" }
            i++
            if (i >= n) break
            c = get(i)
            if (c == ' ') break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m++] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $i" } }
        if (i >= n) break
        i++
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}

class IFenwickTree2(
    val n: Int,
    val m: Int
) {
    val a = Array(n) { IntArray(m) }

    fun sum(x: Int, y: Int): Int { // inclusive
        var sum = 0
        var i = x
        while (i >= 0) {
            var j = y
            while (j >= 0) {
                sum += a[i][j]
                j = (j and (j + 1)) - 1
            }
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun sum(x1: Int, y1: Int, x2: Int, y2: Int): Int = // inclusive
        if (x2 < x1 || y2 < y1) 0 else
            sum(x2, y2) - sum(x2, y1 - 1) - sum(x1 - 1, y2) + sum(x1 - 1, y1 - 1)

    fun update(x: Int, y: Int, delta: Int) {
        var i = x
        while (i < n) {
            var j = y
            while (j < m) {
                a[i][j] += delta
                j = j or (j + 1)
            }
            i = i or (i + 1)
        }
    }
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }

