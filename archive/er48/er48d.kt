package archive.er48

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.splitToIntArray()
    val a = readLine()!!.splitToIntArray()
    val b = readLine()!!.splitToIntArray()
    val c = Array(n) { IntArray(m) }
    for (bit in 0..30) {
        val mask = 1 shl bit
        var i = 0
        var j = 0
        while (true) {
            while (i < n && a[i] and mask == 0) i++
            while (j < m && b[j] and mask == 0) j++
            if (i >= n || j >= m) break
            c[i][j] = c[i][j] or mask
            i++
            j++
        }
        var cnt = 0
        while (i < n) {
            if (a[i] and mask != 0) {
                cnt++
                c[i][0] = c[i][0] or mask
            }
            i++
        }
        while (j < m) {
            if (b[j] and mask != 0) {
                cnt++
                c[0][j] = c[0][j] or mask
            }
            j++
        }
        if (cnt % 2 != 0) {
            println("NO")
            return
        }
    }
    println("YES")
    for (i in 0 until n) println(c[i].joinToString(" "))

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