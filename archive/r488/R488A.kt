package archive.r488

import kotlin.math.*

fun main(args: Array<String>) {
    val a = readLine()!!.splitToIntArray().toRect()
    val b = readLine()!!.splitToIntArray().toRect()
    val h = (b.x1 - b.x0) / 2
    val s0 = b.x1 + b.y0 - h
    val s1 = b.x1 + b.y0 + h
    val d0 = b.x0 - b.y0 - h
    val d1 = b.x0 - b.y0 + h
    for (x in a.x0..a.x1) {
        for (y in a.y0..a.y1) {
            if ((x + y) in s0..s1 && (x - y) in d0..d1) {
                println("YES")
                return
            }
        }
    }
    println("NO")
}

fun IntArray.toRect(): Rect {
    var x0 = Int.MAX_VALUE
    var x1 = Int.MIN_VALUE
    var y0 = Int.MAX_VALUE
    var y1 = Int.MIN_VALUE
    for (i in 0..3) {
        x0 = min(get(2 * i), x0)
        x1 = max(get(2 * i), x1)
        y0 = min(get(2 * i + 1), y0)
        y1 = max(get(2 * i + 1), y1)
    }
    return Rect(x0, x1, y0, y1)
}

class Rect(val x0: Int, val x1: Int, val y0: Int, val y1: Int)

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