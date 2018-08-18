package archive.er48

fun main(args: Array<String>) {
    val (s, a, b) = readLine()!!.splitToIntArray()
    val n = readLine()!!.toInt()
    val l = IntArray(n)
    val r = IntArray(n)
    repeat(n) { i ->
        val (li, ri) = readLine()!!.splitToIntArray()
        l[i] = li
        r[i] = ri
    }
    val t = IntArray(n + 1)
    for (i in 0 until n) {
        t[i + 1] = t[i] + r[i] - l[i]
    }

    fun p0(x: Int, y: Int, a: Int): Double = (a.toDouble() - x) * y / (y - s) + x

    fun searchNext(p0: Double): Int {
        var u = -1
        var v = n
        while (v - u > 1) {
            val w = (u + v) / 2
            if (r[w] < p0) {
                u = w
            } else {
                v = w
            }
        }
        return v // r[v] >= p0
    }

    fun searchPrev(p0: Double): Int {
        var u = -1
        var v = n
        while (v - u > 1) {
            val w = (u + v) / 2
            if (l[w] > p0) {
                v = w
            } else {
                u = w
            }
        }
        return u // l[u] <= p0
    }

    val q = readLine()!!.toInt()
    val res = DoubleArray(q) {
        val (x, y) = readLine()!!.splitToIntArray()
        val pa = p0(x, y, a)
        val pb = p0(x, y, b)
        var u = searchNext(pa)
        var v = searchPrev(pb)
        var ans = 0.0
        if (u == v && l[u] <= pa && r[v] >= pb) {
            ans = (b - a).toDouble()
        } else {
            if (u < n && l[u] < pa) {
                ans += r[u] - pa
                u++
            }
            if (v >= 0 && r[v] > pb) {
                ans += pb - l[v]
                v--
            }
            if (u <= v) {
                ans += t[v + 1] - t[u]
            }
            ans = ans * (y - s) / y
        }
        ans
    }
    println(res.joinToString("\n"))
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