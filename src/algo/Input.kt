package algo

class Input {
    private val input = System.`in`
    private val buf = ByteArray(65536)
    private var pos = 0
    private var rem = 0
    private val eol = Char(10)
    private val eof = Char(0)
    private var ch = eol

    private fun nextBuf() {
        require(ch != eof) { "Reading past end of file" }
        rem = input.read(buf).coerceAtLeast(0)
        pos = 0
    }

    private fun takeByte(): Int {
        if (pos >= rem) nextBuf()
        return if (pos < rem) buf[pos++].toUInt().toInt() else 0
    }

    private fun nextChar() {
        var b = takeByte()
        if (b == 13) b = takeByte()
        ch = b.toChar()
    }

    fun readInt(): Int {
        while (ch == ' ' || ch == eol) nextChar()
        require(ch != eof) { "End of file" }
        var cur = 0
        var neg = false
        if (ch == '-') {
            neg = true
            nextChar()
        }
        while (true) {
            val d = ch.code - '0'.code
            require(d in 0..9) { "Unexpected character '$ch'" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow" }
            nextChar()
            if (ch <= ' ') break
        }
        return if (neg) cur else (-cur).also { require(it >= 0) { "Overflow" } }
    }

    fun readIntArray(n: Int): IntArray = IntArray(n) { readInt() }
}
