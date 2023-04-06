package algo

fun readIntArray(): IntArray {
    val input = System.`in`
    var m = 0
    val eol = '\n'
    var c = eol
    fun nextChar() {
        var b = input.read()
        if (b == '\r'.code) b = input.read()
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
            val d = c.code - '0'.code
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

fun String.splitToIntArray(): IntArray {
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
            val d = c.code - '0'.code
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
            val d = c.code - '0'.code
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