package archive.r668

fun main() {
    System.`in`.bufferedReader().run {
        List(readLine()!!.toInt()) {
            val (n, a, b, da, db) = readLine()!!.split(" ").map { it.toInt() }
            val gf = IntArray(n) { -1 }
            val gn = IntArray(2 * n - 2)
            val gv = IntArray(2 * n - 2)
            fun add(j: Int, u: Int, v: Int) {
                gv[j] = v
                gn[j] = gf[u]
                gf[u] = j
            }
            repeat(n - 1) { j ->
                val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
                add(2 * j, u, v)
                add(2 * j + 1, v, u)
            }
            if (solveB(n, a - 1, b - 1, da, db, gf, gn, gv)) "Alice" else "Bob"
        }.joinToString("\n").let { println(it) }
    }
}

fun solveB(n: Int, a: Int, b: Int, da: Int, db: Int, gf: IntArray, gn: IntArray, gv: IntArray): Boolean {
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
        var j = gf[u]
        while (j >= 0) {
            val v = gv[j]
            if (sd[v] < 0) enqueue(v, sd[u] + 1)
            j = gn[j]
        }
    }
    if (sd[b] <= da) return true
    val md = IntArray(n)
    var mp = 0
    for (i in t - 1 downTo 0) {
        val u = q[i]
        var j = gf[u]
        var d0 = -1
        var d1 = -1
        while (j >= 0) {
            val v = gv[j]
            if (z[v] > z[u]) {
                if (md[v] >= d0) {
                    d1 = d0
                    d0 = md[v]
                } else if (md[v] > d1) {
                    d1 = md[v]
                }
            }
            j = gn[j]
        }
        md[u] = d0 + 1
        mp = maxOf(mp, d0 + d1 + 2)
    }
    return 2 * da >= mp
}

