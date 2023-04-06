package algo

class DFS(val g: Graph) {
    val sv = IntArray(g.vCnt)
    val se = IntArray(g.vCnt)

    inline operator fun invoke(v0: Int, cond: (v: Int, u: Int, e: Int) -> Boolean) {
        var sp = 0
        val e0 = g.vHead[v0]
        if (e0 < 0) return
        sv[0] = v0
        se[0] = e0
        while (sp >= 0) {
            val v = sv[sp]
            val e = se[sp]
            val en = g.eNext[e]
            if (en >= 0) {
                se[sp] = en
            } else {
                sp--
            }
            val u1 = g.eVert[e]
            if (cond(v, u1, e)) {
                val e1 = g.vHead[u1]
                if (e1 >= 0) {
                    sp++
                    sv[sp] = u1
                    se[sp] = e1
                }
            }
        }
    }
}
