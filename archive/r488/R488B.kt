package archive.r488

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.splitToIntArray()
    val a = readLine()!!.processPairs()
    val b = readLine()!!.processPairs()
    val cand = HashSet<Int>()
    var unique = true
    for (i in 1..9) {
        for (ac in a[i]) {
            for (bc in b[i]) {
                if (ac != bc) {
                    cand.add(i)
                    if (!findsOne(i, ac, b) || !findsOne(i, bc, a)) {
                        unique = false
                    }
                }
            }
        }
    }
    if (cand.size == 1) {
        println(cand.single())
        return
    }
    if (unique) {
        println(0)
    } else {
        println(-1)
    }
}

fun findsOne(i: Int, j: Int, b: Array<HashSet<Int>>): Boolean {
    val jc = b[j]
    return jc.isEmpty() || jc.size == 1 && jc.single() == i
}


fun String.processPairs(): Array<HashSet<Int>> {
    val a = splitToIntArray()
    val r = Array<HashSet<Int>>(10) { HashSet<Int>() }
    for (i in 0 until a.size step 2) {
        val u = a[i]
        val v = a[i + 1]
        r[u].add(v)
        r[v].add(u)
    }
    return r
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