package algo

import kotlin.random.Random

// Unknown number of vertices and edges
class Graph2(vCap: Int = 16, eCap: Int = vCap * 2) {
    var vCnt = 0
    var eCnt = 0
    var vHead = IntArray(vCap) { -1 }
    var eVert = IntArray(eCap)
    var eNext = IntArray(eCap)

    fun add(v: Int, u: Int, e: Int = eCnt++) {
        ensureVCap(maxOf(v, u) + 1)
        ensureECap(e + 1)
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

    private fun ensureVCap(vCap: Int) {
        vCnt = maxOf(vCnt, vCap)
        if (vCap <= vHead.size) return
        val newSize = maxOf(2 * vHead.size, vCap)
        vHead = vHead.copyOf(newSize)
    }

    private fun ensureECap(eCap: Int) {
        eCnt = maxOf(eCnt, eCap)
        if (eCap <= eVert.size) return
        val newSize = maxOf(2 * eVert.size, eCap)
        eVert = eVert.copyOf(newSize)
        eNext = eNext.copyOf(newSize)
    }
}

fun main() {
    val n = 1000
    val m = 10000
    val g = Graph2()
    val rnd = Random(1)
    repeat(m) {
        val v = rnd.nextInt(n)
        val u = rnd.nextInt(n)
        g.add(v, u)
    }
}
