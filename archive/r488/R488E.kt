package archive.r488

fun main(args: Array<String>) {
    val (n, x) = readLine()!!.splitToIntArray()
    val a = readLine()!!.splitToIntArray()
    val c = solveOrder(n, a, x)
    println(c.joinToString(" "))
}

fun solveOrder(n: Int, a: IntArray, x: Int): LongArray {
    val l = IntArray(n + 1)
    l[n - 1] = 1
    for (i in n - 2 downTo 0) {
        if (a[i] < x) l[i] = 1 else l[i] = l[i + 1] + 1
    }
    val r = IntArray(n + 1)
    r[n - 1] = 1
    for (i in n - 2 downTo 0) {
        if (a[i + 1] < x) r[i] = 1 else r[i] = r[i + 1] + 1
    }
    var cnt = 0
    val p = IntArray(n + 1)
    val c = LongArray(n + 1)
    c[0] = l[0].toLong() * (l[0] - 1) / 2
    for (i in 0 until n) {
        if (a[i] < x) {
            cnt++
            for (j in 1 until cnt) {
                p[j] += r[p[j]]
            }
            val mul = r[i].toLong()
            for (j in 1..cnt) {
                c[j] += l[p[j]] * mul
            }
            c[0] += mul * (mul - 1) / 2
        }
    }
    return c
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