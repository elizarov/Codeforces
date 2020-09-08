package archive.r668

fun main() {
    System.`in`.bufferedReader().run {
        List(readLine()!!.toInt()) {
            val (n, a, b, da, db) = readLine()!!.split(" ").map { it.toInt() }
            val g = Graph(n, 2 * n - 2)
            repeat(n - 1) { j ->
                val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
                g.add(u, v)
                g.add(v, u)
            }
            if (solveB(n, a - 1, b - 1, da, db, g)) "Alice" else "Bob"
        }.joinToString("\n").let { println(it) }
    }
}

fun solveB(n: Int, a: Int, b: Int, da: Int, db: Int, g: Graph): Boolean {
    if (db < 2 * da + 1) return true
    val sd = IntArray(n) { -1 }
    val z = IntArray(n)
    val q = IntArray(n)
    var h = 0
    var t = 0
    fun enqueue(v: Int, d: Int) {
        sd[v] = d
        z[v] = t
        q[t++] = v
    }
    enqueue(a, 0)
    while (h < t) {
        val u = q[h++]
        g.from(u) { v ->
            if (sd[v] < 0) enqueue(v, sd[u] + 1)
        }
    }
    if (sd[b] <= da) return true
    val md = IntArray(n)
    var mp = 0
    for (i in t - 1 downTo 0) {
        val u = q[i]
        var d0 = -1
        var d1 = -1
        g.from(u) { v ->
            if (z[v] > z[u]) {
                if (md[v] >= d0) {
                    d1 = d0
                    d0 = md[v]
                } else if (md[v] > d1) {
                    d1 = md[v]
                }
            }
        }
        md[u] = d0 + 1
        mp = maxOf(mp, d0 + d1 + 2)
    }
    return 2 * da >= mp
}

class Graph(vCap: Int = 16, eCap: Int = vCap * 2) {
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
        if (vCap > vHead.size) {
            val newSize = maxOf(2 * vHead.size, vCap)
            vHead = vHead.copyOf(newSize)
        }
    }

    private fun ensureECap(eCap: Int) {
        if (eCap > eVert.size) {
            val newSize = maxOf(2 * eVert.size, eCap)
            eVert = eVert.copyOf(newSize)
            eNext = eNext.copyOf(newSize)
        }
    }
}
