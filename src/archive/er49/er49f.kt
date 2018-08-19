package archive.er49

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = IntArray(n)
    val b = IntArray(n)
    repeat(n) { i ->
        val (ai, bi) = readLine()!!.splitToIntArray()
        a[i] = ai
        b[i] = bi
    }
    println(solveSession(n, a, b))
}

fun solveSession(n: Int, a: IntArray, b: IntArray): Int {
    @Suppress("UNCHECKED_CAST")
    val d = arrayOfNulls<D>(2 * n) as Array<D>
    repeat(n) { i ->
        d[2 * i] = D(a[i], i)
        d[2 * i + 1] = D(b[i], i)
    }
    d.sort()
    val ds = IntArray(2 * n + 1)
    var dc = 1
    for (i in 1 until 2 * n) {
        if (d[i].d != d[i - 1].d) {
            ds[dc++] = i
        }
    }
    ds[dc] = 2 * n
    val e = IntArray(n) { -1 }
    val v = BooleanArray(dc)
    val vu = IntArray(dc)
    var vc = 0
    var found = 0

    fun dfs(i: Int): Boolean {
        v[i] = true
        vu[vc++] = i
        for (j in ds[i] until ds[i + 1]) {
            val k = d[j].e
            val w = e[k]
            if (w < 0) {
                e[k] = i
                return true
            }
        }
        for (j in ds[i] until ds[i + 1]) {
            val k = d[j].e
            val w = e[k]
            if (!v[w]) {
                if (dfs(w)) {
                    e[k] = i
                    return true
                }
            }
        }
        return false
    }

    for (i in 0 until dc) {
        if (dfs(i)) {
            if (++found == n) {
                return d[ds[i]].d
            }
        }
        for (j in 0 until vc) {
            v[vu[j]] = false
        }
        vc = 0
    }
    return -1
}

class D(val d: Int, val e: Int): Comparable<D> {
    override fun compareTo(other: D): Int = d - other.d
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