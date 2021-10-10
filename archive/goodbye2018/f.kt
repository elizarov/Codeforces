package archive.goodbye2018

fun main() {
    val n = readLine()!!.toInt()
    val l = readLine()!!.splitToLongArray()
    val s = readLine()!!
    var stMax = 0L
    var tmMax = 0L
    var stMin = 0L
    var tmMin = 0L
    for (i in n - 1 downTo 0) {
        val cost = when(s[i]) {
            'G' -> {
                stMax += l[i]
                tmMax += l[i]
                stMin -= l[i]
                tmMin += 5 * l[i]
                5
            }
            'W' -> {
                stMax += l[i]
                tmMax += l[i]
                stMin -= l[i]
                tmMin += 3 * l[i]
                3
            }
            'L' -> {
                stMax += l[i]
                tmMax += l[i]
                stMin += l[i]
                tmMin += l[i]
                0
            }
            else -> error("!")
        }
        if (stMin < 0) {
            tmMin += (tmMax - tmMin) / (stMax - stMin) * stMax
            stMin = 0L
        } else if (cost > 0) {
            tmMin += cost * stMin
            stMin = 0
        }
    }
    println(tmMin)
}

private fun String.splitToLongArray(): LongArray {
    val n = length
    if (n == 0) return LongArray(0) // EMPTY
    var res = LongArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0L
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
            require(cur >= Long.MIN_VALUE / 10) { "Overflow at $i" }
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