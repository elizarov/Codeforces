package archive.r668

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val d = Array(n) { readLine()!!.toCharArray() }
    val hc = (n - 1) * m
    val vc = (m - 1) * n
    val g = Graph2(hc + vc)
    fun v(i: Int, j: Int) = hc + j * n + i
    var cnt = 0
    for (i in 0 until n - 1) for (j in 0 until m) if (d[i][j] == '#' && d[i + 1][j] == '#') {
        cnt++
        val h = i * m + j
        if (j > 0 && d[i][j - 1] == '#') g.add(h, v(i, j - 1))
        if (j < m - 1 && d[i][j + 1] == '#') g.add(h, v(i, j))
        if (j > 0 && d[i + 1][j - 1] == '#') g.add(h, v(i + 1, j - 1))
        if (j < m - 1 && d[i + 1][j + 1] == '#') g.add(h, v(i + 1, j))
    }
    for (i in 0 until n) for (j in 0 until m - 1) if (d[i][j] == '#' && d[i][j + 1] == '#') {
        cnt++
    }
    val removed = g.maxBiMatching(hc).count { it >= 0 }
    val blocks = d.sumOf { it.count { it == '#' } }
    println(blocks - cnt + removed)
}

private class Graph2(vCap: Int = 16, eCap: Int = vCap * 2) {
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
        if (vCap <= vCnt) return
        vCnt = vCap
        if (vCap > vHead.size) {
            val newSize = maxOf(2 * vHead.size, vCap)
            vHead = vHead.copyOf(newSize)
        }
    }

    private fun ensureECap(eCap: Int) {
        if (eCap <= eCnt) return
        eCnt = eCap
        if (eCap > eVert.size) {
            val newSize = maxOf(2 * eVert.size, eCap)
            eVert = eVert.copyOf(newSize)
            eNext = eNext.copyOf(newSize)
        }
    }
}

private fun Graph2.maxBiMatching(m: Int): IntArray {
    val p = IntArray(m) { -1 } // matching A -> B
    val r = IntArray((vCnt - m).coerceAtLeast(0)) { -1 } // matching B -> A
    val q = IntArray(m) // queue
    val a = IntArray(m) { -1 } // prev vertex in A
    val b = IntArray(m) // prev vertex in B
    for (i in 0 until m) {
        var h = 0
        var t = 1
        q[0] = i
        var fu = -1
        var fv = -1
        while (fu < 0 && h < t) {
            val u = q[h++]
            from(u) { v ->
                val w = r[v - m]
                if (w < 0) {
                    fu = u
                    fv = v
                } else if (a[w] < 0) {
                    a[w] = u
                    b[w] = v
                    q[t++] = w
                }
            }
        }
        while (fu >= 0) {
            p[fu] = fv
            r[fv - m] = fu
            val w = fu
            fu = a[w]
            fv = b[w]
        }
        for (j in 0 until t) {
            a[q[j]] = -1
        }
    }
    return p
}