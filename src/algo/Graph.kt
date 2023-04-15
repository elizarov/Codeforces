package algo

import kotlin.random.Random

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

fun main() {
    val n = 1000
    val m = 10000
    val g = Graph(n, m)
    val rnd = Random(1)
    repeat(m) {
        val v = rnd.nextInt(n)
        val u = rnd.nextInt(n)
        g.add(v, u)
    }
}
