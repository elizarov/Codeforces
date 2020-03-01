package archive.r625

fun main() {
    val (n, m, p) = readLine()!!.split(" ").map { it.toInt() }
    val a = readData(n).minCost()
    val b = readData(m).minCost()
    val (x, y, z) = readMonsters(p)
    val ts = LongArray(4_000_001)
    val tb = LongArray(4_000_001)
    fun update(y: Int, v: Long, ti: Int, tl: Int, tr: Int) {
        ts[ti] += v
        if (tl == tr) {
            tb[ti] = ts[ti]
            return
        }
        val tm = (tl + tr) / 2
        if (y <= tm)
            update(y, v, 2 * ti + 1, tl, tm) else
            update(y, v, 2 * ti + 2, tm + 1, tr)
        tb[ti] = maxOf(tb[2 * ti + 1], ts[2 * ti + 1] + tb[2 * ti + 2])
    }
    fun update(y: Int, v: Long) = update(y, v, 0, 0, 1_000_000)
    update(0, -b[0].toLong())
    for (i in 1..1_000_000) {
        update(i, (b[i - 1] - b[i]).toLong())
    }
    var ans = -a[0].toLong() - b[0]
    for ((i, xi) in x.withIndex().sortedBy { it.value }) {
        val ca = a[xi]
        if (ca == Int.MAX_VALUE) break
        update(y[i], z[i].toLong())
        ans = maxOf(ans, tb[0] - ca)
    }
    println(ans)
}

private data class D(val a: Int, val c: Int)

private fun readData(n: Int): Array<D> =
    Array(n) { i ->
        readLine()!!.split(" ").map { it.toInt() }.let { (ai, ci) -> D(ai, ci) }
    }

private fun Array<D>.minCost(): IntArray {
    val s = sortedBy { it.c } + D(1_000_002, Int.MAX_VALUE)
    val c = IntArray(1_000_001)
    var i = 0
    for (j in c.indices) {
        while (s[i].a <= j) i++
        c[j] = s[i].c
    }
    return c
}

private fun readMonsters(p: Int): Triple<IntArray, IntArray, IntArray> {
    val x = IntArray(p)
    val y = IntArray(p)
    val z = IntArray(p)
    repeat(p) { i ->
        readLine()!!.split(" ").map { it.toInt() }.let { (xi, yi, zi) ->
            x[i] = xi
            y[i] = yi
            z[i] = zi
        }
    }
    return Triple(x, y, z)
}

