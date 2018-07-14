package r492

const val LIM = 1_000_000L

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    var sx = 0L
    var sy = 0L
    val z = Array(17) { ArrayList<P>() }
    for (i in 0 until n) {
        val (x0, y0) = readLine()!!.splitToIntArray()
        sx += x0
        sy += y0
        z[zi(x0.toLong(), y0.toLong())].add(P(i, x0, y0))
    }
    val c = IntArray(n) { 1 }
    while (true) {
        if (sx <= LIM && sx >= -LIM && sy <= LIM && sy >= -LIM) break
        val z0 = zi(sx, sy)
        var found = false
        fl@ for (zi0 in z0 - 3..z0 + 3) {
            val zi = zi0 and 15
            val zl = z[zi]
            if (!zl.isEmpty()) {
                val p = zl.removeAt(zl.lastIndex)
                c[p.i] = -c[p.i]
                sx -= 2 * p.x
                sx -= 2 * p.y
                p.x = -p.x
                p.y = -p.y
                val zj = (zi + 8) and 15
                z[zj].add(p)
                found = true
                break@fl
            }
        }
        if (!found) {
            error("!!!")
        }
    }
    println(c.joinToString(" "))
}

fun zi(x: Long, y: Long) = when {
    y == 0L && x > 0 -> 0
    y > 0 && x > 0 && x > y -> 1
    y > 0 && x > 0 && x == y -> 2
    y > 0 && x > 0 && x < y -> 3
    y > 0 && x == 0L -> 4
    y > 0 && x < 0 && y > -x -> 5
    y > 0 && x < 0 && y == -x -> 6
    y > 0 && x < 0 && y < -x -> 7
    y == 0L && x < 0 -> 8
    y < 0 && x < 0 && x < y -> 9
    y < 0 && x < 0 && x == y -> 10
    y < 0 && x < 0 && x > y -> 11
    y < 0 && x == 0L -> 12
    y < 0 && x > 0 && x < -y -> 13
    y < 0 && x > 0 && x == -y -> 14
    y < 0 && x > 0 && x > -y -> 15
    else -> 16
}

class P(val i: Int, var x: Int, var y: Int)

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