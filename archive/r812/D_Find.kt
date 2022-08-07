class TR(val p: IntArray, val x: Int) {
    val c = IntArray(p.size)

    init {
        go(0, p.size)
    }

    fun go(l: Int, r: Int): Int {
        if (l == r - 1) return l
        val m = (l + r) / 2
        val a = go(l, m)
        val b = go(m, r)
        if (p[a] > p[b]) {
            c[a]++
            return a
        } else {
            c[b]++
            return b
        }
    }
}

val List<TR>.ne: Int get() = if (isEmpty()) 0 else 1

fun find(depth: Int, m: Int, ts: List<TR>, lim: Int): Step? {
    val g = ts.groupBy { it.x }
    when (g.size) {
        0 -> return null
        1 -> return AQ(g.keys.first())
    }
    if (lim <= 0) return null
    fun att(a: Int, b: Int): SQ? {
        val tl = ArrayList<TR>()
        val tm = ArrayList<TR>()
        val tr = ArrayList<TR>()
        for (t in ts) {
            when {
                t.c[a] < t.c[b] -> tl += t
                t.c[a] > t.c[b] -> tr += t
                else -> tm += t
            }
        }
        val cne = tl.ne + tm.ne + tr.ne
        if (cne < 2) return null
        val sl = find(depth + 1, m, tl, lim - 1)
        if (tl.isNotEmpty() && sl == null) return null
        val sm = find(depth + 1, m, tm, lim - 1)
        if (tm.isNotEmpty() && sm == null) return null
        val sr = find(depth + 1, m, tr, lim - 1)
        if (tr.isNotEmpty() && sr == null) return null
        return SQ(a, b, sl, sm, sr)
    }
    for (a in 0 until m) for (b in a + 1 until m) {
        att(a, b)?.let { return it }
    }
    return null
}

fun find(n: Int, lim: Int) : Step {
    val m = 1 shl n
    val ts = ArrayList<TR>()
    val u = IntArray(m + 1) { -1 }
    val p = IntArray(m)
    fun build(i: Int) {
        if (i == m) {
            ts.add(TR(p.copyOf(), u[m]))
            return
        }
        for (j in 1..m) if (u[j] == -1) {
            u[j] = i
            p[i] = j
            build(i + 1)
            u[j] = -1
        }
    }
    build(0)
    return find(0, m, ts, lim)!!
}

fun main() {
    val s2 = find(2, 2)
    val s3 = find(3, 4)
    println("val s2 = $s2")
    println("val s3 = $s3")
}