fun main() {
    class Graph(val vCnt: Int, val eCnt: Int) {
        private var eCur = 0
        var vHead = IntArray(vCnt) { -1 }
        var vDeg = IntArray(vCnt)
        var eVert = IntArray(eCnt)
        var eNext = IntArray(eCnt)

        fun add(v: Int, u: Int, e: Int = eCur++) {
            eVert[e] = u
            eNext[e] = vHead[v]
            vHead[v] = e
            vDeg[v]++
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
            g.add(u, v)
            g.add(v, u)
        }
        val top = g.vDeg.withIndex().sortedByDescending { it.value }
        var res = 0
        for ((i0, d0) in top) {
            val n0 = HashSet<Int>()
            g.from(i0) { n0 += it }
            for ((i1, d1) in top) if (i1 != i0) {
                var k = d0 + d1 - 2
                val nn = i1 !in n0
                if (nn) k++
                res = maxOf(k, res)
                if (nn) break
            }
        }
        println(res)
    }
}