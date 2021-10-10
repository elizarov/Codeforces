package archive.r564

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.splitToIntArray() // hand
    val b = readLine()!!.splitToIntArray() // pile
    println(solveArr(n, b, a))
}

private fun solveArr(n: Int, b: IntArray, a: IntArray): Int {
    val start = findStart(b, n)
    val hs = BooleanArray(n + 1)
    for (x in a) if (x != 0) hs[x] = true
    var ok = true
    for (step in 0 until start) {
        val next = n - start + 1 + step
        if (!hs[next]) {
            ok = false
            break
        }
        hs[b[step]] = true
    }
    if (ok) return start
    var maxD = 0
    for (i in 0 until n) {
        if (b[i] != 0) {
            val d = 2 + i - b[i]
            if (d > maxD) {
                maxD = d
            }
        }
    }
    return maxD + n
}

fun findStart(b: IntArray, n: Int): Int {
    val i = b.indexOf(1)
    if (i < 0) return n
    var j = i
    while (j < n && b[j] == j - i + 1) {
        j++
    }
    return if (j == n) i else n
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