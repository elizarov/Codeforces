package archive.er58

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.splitToIntArray()
    val g = Array(n) { ArrayList<Int>() }
    repeat(n - 1) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[x].add(y)
        g[y].add(x)
    }
    println(solveTree(a, g))
}

fun solveTree(a: IntArray, g: Array<ArrayList<Int>>): Int {
    val m = a.max()!!
    val p = IntArray(m)
    var pc = 1
    p[0] = 2
    var d = 3
    while (d <= m) {
        var prime = true
        var k = 0
        while (p[k] * p[k] <= d) {
            if (d % p[k] == 0) {
                prime = false
                break
            }
            k++
        }
        if (prime) p[pc++] = d
        d += 2
    }

    var max = 0

    fun find(d: Int, i: Int, p: Int): Int {
        var len0 = 0
        var len1 = 0
        for (j in g[i]) if (j != p) {
            val len = find(d, j, i)
            when {
                len >= len0 -> {
                    len1 = len0
                    len0 = len
                }
                len >= len1 -> len1 = len
            }
        }
        if (a[i] % d != 0) return 0
        val res = 1 + len0 + len1
        if (res > max) max = res
        return res
    }

    for (k in 0 until pc) {
        find(p[k], 0, -1)
    }
    return max
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
            require(d in 0..9) { "Unexpected character '$c' at $i" }
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