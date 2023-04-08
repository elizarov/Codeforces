import java.util.*

fun main() {
    val inp = Input()
    val (n, m) = inp.readIntArray(2)
    val a = inp.readIntArray(n)
    val g = Graph(n, 2 * (n - 1))
    repeat(n - 1) {
        val u = inp.readInt() - 1
        val v = inp.readInt() - 1
        g.add(u, v)
        g.add(v, u)
    }
    val p = IntArray(n) { -1 }
    val szs = IntArray(n) { 1 }
    val ims = LongArray(n) { a[it].toLong() }
    val dfs = DFS(g)
    dfs(0,
        cond = { v, u, _ ->
            if (u != 0 && p[u] < 0) {
                p[u] = v
                true
            } else false
        },
        exit = { u ->
            if (p[u] >= 0) {
                ims[p[u]] += ims[u]
                szs[p[u]] += szs[u]
            }
        }
    )
    data class Item(val idx: Int, val sz: Int) : Comparable<Item> {
        override fun compareTo(other: Item): Int {
            val c = other.sz - sz
            if (c != 0) return c
            return idx - other.idx
        }
    }
    val ts = Array(n) { TreeSet<Item>() }
    for (i in 1 until n) {
        ts[p[i]] += Item(i, szs[i])
    }
    val ans = ArrayList<Long>()
    repeat(m) {
        when (inp.readInt()) {
            1 -> {
                val x = inp.readInt() - 1
                ans += ims[x]
            }
            2 -> {
                val x = inp.readInt() - 1
                val xts = ts[x]
                if (xts.isNotEmpty()) {
                    val si = xts.first()!!
                    val s = si.idx
                    val f = p[x]
                    val xi = Item(x, szs[x])
                    val fts = ts[f]
                    val sts = ts[s]

                    fts.remove(xi)
                    xts.remove(si)

                    szs[x] -= szs[s]
                    ims[x] -= ims[s]

                    p[x] = s
                    p[s] = f

                    szs[s] += szs[x]
                    ims[s] += ims[x]

                    fts.add(Item(s, szs[s]))
                    sts.add(Item(x, szs[x]))
                }
            }
        }
    }
    println(ans.joinToString("\n"))
}

class DFS(val g: Graph) {
    val sv = IntArray(g.vCnt)
    val se = IntArray(g.vCnt)

    inline operator fun invoke(v0: Int, cond: (v: Int, u: Int, e: Int) -> Boolean, exit: (Int) -> Unit) {
        var sp = 0
        val e0 = g.vHead[v0]
        sv[0] = v0
        se[0] = e0
        while (sp >= 0) {
            val v = sv[sp]
            val e = se[sp]
            if (e < 0) {
                exit(v)
                sp--
                continue
            }
            se[sp] = g.eNext[e]
            val u1 = g.eVert[e]
            if (cond(v, u1, e)) {
                val e1 = g.vHead[u1]
                if (e1 >= 0) {
                    sp++
                    sv[sp] = u1
                    se[sp] = e1
                }
            }
        }
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
