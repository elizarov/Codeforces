package archive.er49

private const val N = 10_001

fun main(args: Array<String>) {
    val tests = readLine()!!.toInt()
    val cnt = IntArray(N)
    val nz = IntArray(N)
    val g2 = IntArray(N)
    val ans = Array(tests) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.splitToIntArray()
        var m1 = -1
        var nzc = 0
        var g2c = 0
        for (x in a) {
            if (cnt[x]++ == 0) {
                nz[nzc++] = x
            }
            if (cnt[x] == 2) {
                g2[g2c++] = x
            }
            if (cnt[x] >= 4) {
                m1 = x
            }
        }
        var m2 = -1
        if (m1 >= 0) {
            m2 = m1
        } else {
            var best = Double.MAX_VALUE
            g2.sort(0, g2c)
            for (i in 0..g2c - 2) {
                val cur = g2[i + 1].toDouble() / g2[i]
                if (cur < best) {
                    m1 = g2[i]
                    m2 = g2[i + 1]
                    best = cur
                }
            }
        }
        for (i in 0 until nzc) {
            cnt[nz[i]] = 0
        }
        nz.fill(0, 0, nzc)
        g2.fill(0, 0, g2c)
        "$m1 $m1 $m2 $m2"
    }
    println(ans.joinToString("\n"))
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