package er47

import java.io.BufferedReader
import java.io.PrintWriter
import kotlin.math.max

fun main(args: Array<String>) {
    PrintWriter(System.`out`).use { ouf ->
        solveF(System.`in`.bufferedReader(), ouf)
    }
}

fun solveF(inf: BufferedReader, ouf: PrintWriter) {
    val n = inf.readLine()!!.toInt()
    val g = Array(n) { IntArrayList() }
    repeat(n - 1) {
        val (x, y) = inf.readLine()!!.splitToIntArray()
        g[x - 1].add(y - 1)
        g[y - 1].add(x - 1)
    }
    val f = BooleanArray(n)
    val p = IntArray(n)
    val q = IntArray(n)
    val d = IntArray(n)
    var h = 0
    var t = 1
    p[0] = -1
    f[0] = true
    while (h < t) {
        val i = q[h]
        h++
        for (j in g[i]) {
            if (f[j]) continue
            f[j] = true
            q[t] = j
            d[j] = d[i] + 1
            p[j] = i
            t++
        }
    }
    check(h == n)
    val md = d.copyOf()
    for (k in n - 1 downTo 1) {
        val i = q[k]
        md[p[i]] = max(md[p[i]], md[i])
    }
    val d0 = d.copyOf()
    val mj = IntArray(n) { -1 }
    for (k in 0 until n) {
        val i = q[k]
        val mdi = md[i]
        for (j in g[i]) {
            if (d[j] < d[i]) continue
            if (md[j] == mdi) {
                mj[i] = j
                break
            }
        }
        if (mj[i] >= 0) {
            d0[mj[i]] = d0[i]
        }
    }
    val a = arrayOfNulls<MA?>(n)
    val r = IntArray(n)
    for (k in n - 1 downTo 0) {
        val i = q[k]
        val di = d[i]
        val mji = mj[i]
        val mdi = md[i]
        val d0i = d0[i]
        val ai = if (mji >= 0) a[mji]!! else MA(mdi - d0i + 1)
        a[i] = ai
        var cc = 0
        for (j in g[i]) {
            if (d[j] < d[i]) continue
            cc++
            if (j != mji) {
                val aj = a[j]!!
                val dj = d[j]
                check(d0[j] == dj)
                check(dj == di + 1)
                val ofsj = dj - d0i
                for (t in 0 until aj.a.size) {
                    ai.add(ofsj + t, aj[t])
                }
            }
        }
        val ofsi = di - d0i
        ai.add(ofsi, cc)
        r[i] = if (ai.max() <= 1) 0 else (ai.maxIdx - ofsi + 1).coerceAtLeast(0)
    }
    for (x in r) ouf.println(x)
}

class MA(n: Int) {
    val a = IntArray(n)
    var maxIdx = 0

    operator fun get(i: Int) = a[i]
    fun max() = a[maxIdx]

    fun add(i: Int, x: Int) {
        a[i] += x
        if (a[i] > a[maxIdx] || a[i] == a[maxIdx] && i < maxIdx)
            maxIdx = i
    }
}

class IntArrayList(capacity: Int = 2) {
    private var a = IntArray(capacity)

    var size: Int = 0
        private set

    fun add(x: Int) {
        if (size >= a.size) a = a.copyOf(size * 2)
        a[size++] = x
    }

    operator fun iterator(): IntIterator = object : IntIterator() {
        var i = 0
        override fun hasNext(): Boolean = i < size
        override fun nextInt(): Int = a[i++]
    }
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