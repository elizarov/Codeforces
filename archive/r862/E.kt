import java.io.PrintStream

fun main() {
    SolveE().apply {
        solve()
        output()
    }
}

class Input {
    private val input = System.`in`
    private val buf = ByteArray(65536)
    private var pos = 0
    private var rem = 0
    private val eol = Char(13)
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
        if (b == 10) b = takeByte()
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

class SolveE {
    val inp = Input()
//    val n = readln().toInt()
    val n = inp.readInt()

    val g = Graph(n, 2 * n - 2)

    init {
        repeat(n - 1) {
//        val (u, v) = readln().split(" ").map { it.toInt() - 1 }
            val u = inp.readInt() - 1
            val v = inp.readInt() - 1
            g.add(u, v)
            g.add(v, u)
        }
    }

//    val a = readln().split(" ").map { it.toInt() }.toIntArray()
    val a = inp.readIntArray(n)

    lateinit var ans: IntArray

    fun solve() {
        ans = solveE(n, g, a)
    }

    fun output(out: PrintStream = System.out) {
        out.println(ans.joinToString("\n"))
    }

}

fun solveE(n: Int, g: Graph, a: IntArray): IntArray {
    data class IV(val index: Int, val value: Int)

//    val b = a.mapIndexed { i, v -> IV(i, v) }.groupBy { it.value }.toList().sortedByDescending { it.first }

    val bs = a.mapIndexed { i, v -> IV(i, v) }.sortedByDescending { it.value }

    data class IVS(val i: Int, val value: Int, val size: Int)
    val b = buildList {
        var i = 0
        while (i < bs.size) {
            val cur = bs[i]
            var j = i + 1
            while (j < bs.size && cur.value == bs[j].value) j++
            add(IVS(i, cur.value, j - i))
            i = j
        }
    }

    val j3 = b.indexOfFirst { it.size >= 3 }.takeIf { it >= 0 } ?: b.size
    val w3 = b.getOrNull(j3)?.value ?: 0
    val j2 = b.indexOfFirst { it.size == 2 }
    val ans = IntArray(n - 1) { w3 }
    if (j2 < 0 || j2 > j3) return ans
    val w2  = b[j2].value
    ans.fill(w2)
    val p = IntArray(n) { -1 }
    val pe = IntArray(n) { -1 }

//    val dfsParent= DeepRecursiveFunction<Int, Unit> { v ->
//        g.from(v) { u, e ->
//            if (u != 0 && p[u] < 0) {
//                p[u] = v
//                pe[u] = e / 2
//                callRecursive(u)
//            }
//        }
//    }
//    dfsParent(0)

    val dfs = DFS(g)
    dfs(0) { v, u, e ->
        if (u != 0 && p[u] < 0) {
            p[u] = v
            pe[u] = e / 2
            true
        } else false
    }

//    val (vl, vr) = b[j2].second.map { it.index }
    val vl = bs[b[j2].i].index
    val vr = bs[b[j2].i + 1].index

    fun up(v0: Int): List<Int> {
        val res = ArrayList<Int>()
        res += v0
        var v = v0
        while (p[v] >= 0) {
            res += pe[v]
            v = p[v]
            res += v
            if (v == vl || v == vr) break
        }
        return res
    }
    val ul = up(vl)
    val ur = up(vr)
    val path = when {
        ul.last() == vr -> ul
        ur.last() == vl -> ur
        else -> {
            var il = ul.lastIndex
            var ir = ur.lastIndex
            while (il > 0 && ir > 0 && ul[il - 2] == ur[ir - 2]) { il -= 2; ir -= 2 }
            ul.subList(0, il + 1) + ur.subList(0, ir).reversed()
        }
    }
    val vpi = IntArray(n) { -1 }
    for (i in path.indices step 2) {
        vpi[path[i]] = i
    }
    var fillIndex = 0

//    val dfsFillVpi = DeepRecursiveFunction<Int, Unit> { v ->
//        g.from(v) { u, _ ->
//            if (vpi[u] < 0) {
//                vpi[u] = fillIndex
//                callRecursive(u)
//            }
//        }
//    }

    fun dfsFillVpi(v0: Int) = dfs(v0) { v, u, _ ->
        if (vpi[u] < 0) {
            vpi[u] = fillIndex
            true
        } else false
    }

    while (fillIndex < path.size) {
        dfsFillVpi(path[fillIndex])
        fillIndex += 2
    }
    var il = 0
    var ir = path.lastIndex
    for (j in j2 + 1 until j3) if (b[j].size == 2) {
        val w = b[j].value

//        var (i1, i2) = b[j].second.map { vpi[it.index] }
        var i1 = vpi[bs[b[j].i].index]
        var i2 = vpi[bs[b[j].i + 1].index]

        if (i1 > i2) i1 = i2.also { i2 = i1 }
        i1 = i1.coerceIn(il, ir)
        i2 = i2.coerceIn(il, ir)
        for (i in il + 1 until i1 step 2) {
            ans[path[i]] = w
        }
        for (i in i2 + 1 until ir step 2) {
            ans[path[i]] = w
        }
        il = i1
        ir = i2
    }
    for (i in il + 1 until ir step 2) {
        ans[path[i]] = w3
    }
    return ans
}

/*
wrong answer 2440th numbers differ -
expected: '271531732',
   found: '271492149'

 */

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

    inline fun from(v: Int, action: (u: Int, e: Int) -> Unit) {
        var e = vHead[v]
        while (e >= 0) {
            action(eVert[e], e)
            e = eNext[e]
        }
    }
}

class DFS(val g: Graph) {
    val sv = IntArray(g.vCnt)
    val se = IntArray(g.vCnt)

    inline operator fun invoke(v0: Int, cond: (v: Int, u: Int, e: Int) -> Boolean) {
        var sp = 0
        val e0 = g.vHead[v0]
        if (e0 < 0) return
        sv[0] = v0
        se[0] = e0
        while (sp >= 0) {
            val v = sv[sp]
            val e = se[sp]
            val en = g.eNext[e]
            if (en >= 0) {
                se[sp] = en
            } else {
                sp--
            }
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
