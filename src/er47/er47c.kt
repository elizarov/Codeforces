package er47

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.splitToIntArray()
    val mid = (n + 1) / 2
    val maxF = sumDist(n - 1)
    val minF = sumDist(n - mid) + sumDist(mid - 1)
    var sum = 0L
    repeat(m) {
        val (x, d) = readLine()!!.splitToIntArray()
        sum += x.toLong() * n
        sum += if (d >= 0) d * maxF else d * minF
    }
    println(sum.toDouble() / n)
}

private fun sumDist(n: Int) = n.toLong() * (n + 1) / 2

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