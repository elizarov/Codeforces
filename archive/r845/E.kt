data class Edge(val v: Int, val w: Int)

fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val g = GraphW(n, 2 * m)
        repeat(m) {
            var (u, v, w) = readln().split(" ").map { it.toInt() }
            u--
            v--
            g.add(u, v, w)
            g.add(v, u, -w)
        }
        println(solveE(n, g))
    }
}

class DFS(val g: GraphW) {
    val n = g.vCnt
    val su = IntArray(n)
    val se = IntArray(n)
    var st = 0

    inline operator fun invoke(
        v0: Int,

        crossinline pre: (Int) -> Unit,
        crossinline visit: (v: Int, w: Int) -> Boolean,
        crossinline post: (Int) -> Unit
    ) {
//        val rec = DeepRecursiveFunction { u ->
//            pre(u)
//            g.from(u) { v, w ->
//                if (visit(v, w)) {
//                    callRecursive(v)
//                }
//            }
//            post(u)
//        }
//        rec(v0)

        st = -1
        push(v0)
        pre(v0)
        loop@while (st >= 0) {
            val u = su[st]
            var e = se[st]
            while (e >= 0) {
                val v = g.eVert[e]
                val w = g.eWght[e]
                e = g.eNext[e]
                se[st] = e
                if (visit(v, w)) {
                    push(v)
                    pre(v)
                    continue@loop
                }
            }
            post(u)
            st--
        }
    }

    fun push(u: Int) {
        st++
        su[st] = u
        se[st] = g.vHead[u]
    }
}

fun solveE(n: Int, g: GraphW): Int {
    val wl = ArrayList<Int>()
    for (v in 0 until n) g.from(v) { _, w -> if (w > 0) wl += w }
    wl += 0
    wl.sort()
    var l = -1
    var r = wl.size
    val f = BooleanArray(n)
    val ord = IntArray(n)
    var oi: Int
    var t: Int
    val col = IntArray(n)
    var cc: Int
    val ideg = IntArray(n)
    val dfs = DFS(g)
    while (r - l > 1) {
        val mid = (r + l) / 2
        t = wl[mid]
        f.fill(false)
        oi = 0
        for (v0 in 0 until n) if (!f[v0]) {
            dfs(
                v0,
                pre = { u -> f[u] = true },
                visit = { v, w -> !f[v] && w <= t },
                post = { u -> ord[oi++] = u }
            )
        }
        col.fill(-1)
        cc = 0
        for (j in oi - 1 downTo 0) {
            val v0 = ord[j]
            if (col[v0] < 0) {
                dfs(
                    v0,
                    pre = { u -> col[u] = cc },
                    visit = { v, w -> col[v] < 0 && w >= -t },
                    post = {}
                )
                cc++
            }
        }
        ideg.fill(0, toIndex = cc)
        for (u in 0 until n) g.from(u) { v, w ->
            if (w >= -t && col[u] != col[v]) {
                ideg[col[v]]++
            }
        }
        val ok = (0 until cc).count { i -> ideg[i] == 0 } == 1
        if (ok) {
            r = mid
        } else {
            l = mid
        }
    }
    return if (r == wl.size) -1 else wl[r]
}

// Weighted graph
class GraphW(vCap: Int, eCap: Int) {
    var vCnt = vCap
    var eCnt = 0
    var vHead = IntArray(vCap) { -1 }
    var eVert = IntArray(eCap)
    var eNext = IntArray(eCap)
    var eWght = IntArray(eCap)

    fun add(v: Int, u: Int, w: Int, e: Int = eCnt++) {
        eVert[e] = u
        eNext[e] = vHead[v]
        eWght[e] = w
        vHead[v] = e
    }

    inline fun from(v: Int, action: (u: Int, w: Int) -> Unit) {
        var e = vHead[v]
        while (e >= 0) {
            action(eVert[e], eWght[e])
            e = eNext[e]
        }
    }
}