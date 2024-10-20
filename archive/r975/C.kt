fun main() {
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

    repeat(readln().toInt()) {
        val n = readln().toInt()
        val g = Graph(n, 2 * n - 2)
        repeat(n - 1) {
            val (u, v) = readln().split(" ").map { it.toInt() - 1 }
            g.add(v, u)
            g.add(u, v)
        }
        val du = IntArray(n)
        val ds = IntArray(n) { 1 }
        val p = IntArray(n) { -1 }
        val dfs = DeepRecursiveFunction<Int, Unit> { v ->
            g.from(v) { u ->
                if (u != p[v]) {
                    p[u] = v
                    du[u] = du[v] + 1
                    callRecursive(u)
                    ds[v] += ds[u]
                }
            }
        }
        var d = Int.MAX_VALUE
        for (i in 0 until n) if (ds[i] == 1) d = minOf(d, du[i])
        var ans = 0
        for (i in 0 until n) if (du[i] == d) ans += ds[i]
        println(ans)
    }
}