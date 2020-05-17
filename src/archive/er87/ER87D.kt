package archive.er87

fun main() {
    val (n, q) = readIntArray()
    val a = readIntArray()
    val k = readIntArray()
    val f = IFenwickTree(n + 1)
    for (x in a) f.update(x, 1)
    for (z in k) {
        if (z >= 1) {
            f.update(z, 1)
        } else {
            val y = findStat(f, n, -z)
            f.update(y, -1)
        }
    }
    val ans = findStat(f, n, 1)
    println(if (ans == n + 1) 0 else ans)
}

private fun findStat(f: IFenwickTree, n: Int, ord: Int): Int {
    var l = 0
    var r = n + 1
    while (l < r - 1) {
        val m = (l + r) / 2
        val cnt = f.sum(m)
        if (cnt < ord) {
            l = m
        } else {
            r = m
        }
    }
    return r
}

class IFenwickTree(n: Int) {
    val a = IntArray(n)

   fun sum(toIndex: Int): Int { // inclusive
        var i = toIndex
        var sum = 0
        while (i >= 0) {
            sum += a[i]
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun update(index: Int, delta: Int) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}

private fun readIntArray(): IntArray {
    val input = System.`in`
    var m = 0
    val eol = '\n'
    var c = eol
    fun nextChar() {
        var b = input.read()
        if (b == '\r'.toInt()) b = input.read()
        c = if (b < 0) eol else b.toChar()
    }
    nextChar()
    var res = IntArray(4)
    while (c != eol) {
        var cur = 0
        var neg = false
        if (c == '-') {
            neg = true
            nextChar()
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d in 0..9) { "Unexpected character '$c' at $m" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $m" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $m" }
            nextChar()
            if (c == ' ' || c == eol) break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $m" } }
        m++
        while (c == ' ') nextChar()
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}