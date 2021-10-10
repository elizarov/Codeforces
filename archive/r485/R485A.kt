fun main(args: Array<String>) {
    val (n, m, k, s) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.split(" ").map { it.toInt() - 1 }.toIntArray()
    val g0 = List(n) { ArrayList<Int>() }
    repeat(m) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g0[u] += v
        g0[v] += u
    }
    val r = solvePow(n, k, s, a, g0)
    println(r.joinToString(" "))
}

fun solvePow(
    n: Int,
    k: Int,
    s: Int,
    a: IntArray,
    g0: List<ArrayList<Int>>
): IntArray {
    val g = Array(n) { g0[it].toIntArray() }
    val f = BooleanArray(n)
    val q = IntArray(2 * n)
    val c = Array(n) { IntArray(k) }
    for (w in 0 until k) {
        f.fill(false)
        var h = 0
        var t = 0
        for (i in 0 until n) {
            if (a[i] == w) {
                f[i] = true
                q[t++] = i
                q[t++] = 0
            }
        }
        while (h < t) {
            val u = q[h++]
            val d = q[h++] + 1
            for (v in g[u]) {
                if (!f[v]) {
                    f[v] = true
                    q[t++] = v
                    q[t++] = d
                    c[v][w] = d
                }
            }
        }
    }
    val r = IntArray(n)
    for (i in 0 until n) {
        val d = c[i]
        d.sort()
        for (j in 0 until s) r[i] += d[j]
    }
    return r
}