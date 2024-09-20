import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val g = Graph(n, 2 * n - 2)
        repeat(n - 1) {
            val (a, b) = readln().split(" ").map { it.toInt() - 1 }
            g.add(a, b)
            g.add(b, a)
        }
        val p = IntArray(n) { -1 }
        val d = IntArray(n)
        val dfs = DeepRecursiveFunction { v ->
            g.from(v) { u ->
                if (p[u] < 0) {
                    p[u] = v
                    callRecursive(u)
                    d[v] = max(d[v], d[u] + 1)
                }
            }
        }
        dfs(0)
        val (u, v) = readln().split(" ").map { it.toInt() - 1 }
        when (solve(n, g, p, d, v)) {
            0 -> println("Alice")
            1 -> println("Bob")
        }
    }
}

fun solve(n: Int, g: Graph, p: IntArray, d: IntArray, v: Int): Int {
    var up = 0
    var cur = v
    while (cur != 0) {
        up++
        cur = p[cur]
    }
    val dd = IntArray(up + 1)
    cur = v
    var prev = -1
    for (k in 0..up) {
        dd[k] = 0
        g.from(cur) { u ->
            if (u != p[cur] && u != prev) {
                dd[k] = max(dd[k], d[u] + 1)
            }
        }
        prev = cur
        cur = p[cur]
    }
    val da = IntArray(up + 1)
    for (k in up downTo 0) {
        da[k] = dd[k] + up - k
        if (k < up) da[k] = max(da[k], da[k + 1])
    }
    val bup = (up - 1) / 2
    for (k in 0..bup) {
        val bob = dd[k] + k
        val alice = da[k + 1]
        if (bob >= alice) return 1
    }
    return 0
}

class Graph(val vCnt: Int, val eCnt: Int) {
    private var eCur = 0
    var vHead = IntArray(vCnt) { -1 }
    var eVert = IntArray(eCnt)
    var eNext = IntArray(eCnt)

    fun add(v: Int, u: Int, e: Int = eCur++) {
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
