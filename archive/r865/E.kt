fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val g = Graph(n, m)
        repeat(m) {
            val (a, b) = readln().split(" ").map { it.toInt() - 1 }
            g.add(b, a)
        }
        val dv = IntArray(n) { -1 }
        val q = IntArray(n)
        var qh = 0
        var qt = 0
        fun enq(v: Int, d: Int) {
            q[qt++] = v
            dv[v] = d
        }
        enq(0, 0)
        while (qt > qh) {
            val v = q[qh++]
            g.from(v) { u ->
                if (dv[u] < 0) enq(u, dv[v] + 1)
            }
        }
        if (dv.any { it < 0 }) {
            println("INFINITE")
        } else {
            println("FINITE")
            val md = dv.max()
            val map = dv.withIndex().groupBy { it.value }.mapValues { e -> e.value.map { it.index + 1 } }
            val ans = ArrayList<Int>()
            for (gr in 1..md + 1) {
                for (cd in md downTo gr - 1) {
                    ans += map[cd]!!
                }
            }
            println(ans.size)
            println(ans.joinToString(" "))
        }
    }
}
class Graph(vCnt: Int, eCap: Int) {
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
