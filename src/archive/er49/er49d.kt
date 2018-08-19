package archive.er49

import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val c = readLine()!!.splitToIntArray()
    val a = readLine()!!.splitToIntArray()
    val v = IntArray(n)
    var vc = 0
    var sum = 0L
    for (i in 0 until n) {
        if (v[i] > 0) continue
        vc++
        var j = i
        while (v[j] == 0) {
            v[j] = vc
            j = a[j] - 1
        }
        if (v[j] != vc) continue
        var best = c[j]
        var k = a[j] - 1
        while (k != j) {
            best = min(best, c[k])
            k = a[k] - 1
        }
        sum += best
    }
    println(sum)
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