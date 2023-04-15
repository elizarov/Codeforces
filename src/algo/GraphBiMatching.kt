package algo

// Graph is bipartite
// Partition A: 0 until m
// Partition B: m until vCnt
// Only edges from A to B are used
// Returns matching A -> B
fun Graph.maxBiMatching(m: Int): IntArray {
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

// test
fun main() {
    val g = Graph(8, 7)
    g.add(0, 4)
    g.add(0, 6)
    g.add(0, 5)
    g.add(1, 4)
    g.add(1, 5)
    g.add(2, 5)
    g.add(3, 6)
    val p = g.maxBiMatching(4)
    check(intArrayOf(6, 4, 5, -1).contentEquals(p))
}