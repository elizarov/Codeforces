package algo

import kotlin.random.Random

// Weighted graph
class GraphW(vCap: Int = 16, eCap: Int = vCap * 2) {
    var vCnt = 0
    var eCnt = 0
    var vHead = IntArray(vCap) { -1 }
    var eVert = IntArray(eCap)
    var eNext = IntArray(eCap)
    var eWght = IntArray(eCap)

    fun add(v: Int, u: Int, w: Int, e: Int = eCnt++) {
        ensureVCap(maxOf(v, u) + 1)
        ensureECap(e + 1)
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
        eWght = eWght.copyOf(newSize)
    }
}

fun main() {
    val n = 1000
    val m = 10000
    val g = GraphW()
    val rnd = Random(1)
    repeat(m) {
        val v = rnd.nextInt(n)
        val u = rnd.nextInt(n)
        val w = rnd.nextInt(1_000_000_000)
        g.add(v, u, w)
    }
}
