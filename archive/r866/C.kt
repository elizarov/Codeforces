fun main() {
    val inp = Input()
    val n = inp.readInt()
    val g = Graph(n, 2 * n - 2)
    repeat(n - 1) {
        val u = inp.readInt() - 1
        val v = inp.readInt() - 1
        g.add(u, v)
        g.add(v, u)
    }
    val f = BooleanArray(n)
    val g2 = Graph(n, 2 * n)
    fun link(a: Int, b: Int) {
        g2.add(a, b)
        g2.add(b, a)
    }
    val v0 = (0 until n).first { v -> g.eNext[g.vHead[v]] < 0 }
    data class P(val a: Int, val b: Int, val d: Boolean = false)
    val dfs = DeepRecursiveFunction<Int, P?> rec@{ v ->
        f[v] = true
        val ps = ArrayList<P>()
        var _x: P? = null
        var _y: P? = null
        g.from(v) { u ->
            if (!f[u]) {
                val rp = callRecursive(u) ?: return@rec null
                if (rp.a != rp.b) {
                    when {
                        _x == null -> _x = rp
                        _y == null -> _y = rp
                        else -> return@rec null
                    }
                } else {
                    ps += rp
                }
            }
        }
        if (_x?.d == true && _y?.d == false) _x = _y.also { _y = _x }
        val x = _x
        val y = _y
        val m = ps.size
        for (i in 0..m - 2) link(ps[i].b, ps[i + 1].a)
        if (x == null) {
            if (m == 0) return@rec P(v, v)
            link(v, ps[0].a)
            return@rec P(v, ps[m - 1].b)
        }
        if (y == null) {
            link(v, x.b)
            if (m == 0) return@rec if (x.d && v != v0) null else P(v, x.a)
            if (x.d) return@rec null
            link(x.a, ps[0].a)
            return@rec P(v, ps[m - 1].b)
        }
        link(x.b, v)
        link(v, y.b)
        if (m == 0) return@rec if (x.d || y.d) null else P(x.a, y.a, true)
        link(y.a, ps[0].a)
        if (x.d) null else P(x.a, ps[m - 1].b, true)
    }
    val ans = dfs(v0)
    if (ans == null) {
        println("No")
    } else {
        println("Yes")
        link(ans.b, ans.a)
        val ans = IntArray(n)
        var c = 0
        f.fill(false)
        for (i in 0 until n) {
            ans[i] = c
            f[c] = true
            var ok = false
            g2.from(c) { v ->
                if (!ok && !f[v]) {
                    ok = true
                    c = v
                }
            }
        }
        println(ans.joinToString(" ") { (it + 1).toString() })
    }
}

class Graph(val vCnt: Int, eCap: Int) {
    var eCnt = 0
    var vHead = IntArray(vCnt) { -1 }
    var eVert = IntArray(eCap)
    var eNext = IntArray(eCap)

    fun add(v: Int, u: Int, e: Int = eCnt++) {
        eVert[e] = u
        eNext[e] = vHead[v]
        vHead[v] = e
    }

    inline fun from(v: Int, action: (u: Int) -> Unit) {
        var e = vHead[v]
        while (e >= 0) {
            action(eVert[e])
            e = eNext[e]
        }
    }
}

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
