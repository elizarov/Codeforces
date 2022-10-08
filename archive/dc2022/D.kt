fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val u = IntArray(m)
        val v = IntArray(m)
        val w = IntArray(m)
        val g = Array(n) { ArrayList<Int>() }
        val mn = IntArray(n) { Int.MAX_VALUE }
        repeat(m) { i ->
            val (ui, vi, wi) = readln().split(" ").map { it.toInt() }
            u[i] = ui - 1
            v[i] = vi - 1
            w[i] = wi
            g[u[i]].add(v[i])
            g[v[i]].add(u[i])
            mn[u[i]] = minOf(mn[u[i]], w[i])
            mn[v[i]] = minOf(mn[v[i]], w[i])
        }
        val d = Array(n) { IntArray(n) { n } }
        val q = IntArray(n)
        val f = BooleanArray(n)
        for (start in 0 until n) {
            val ds = d[start]
            var qh = 0
            var qt = 1
            q[0] = start
            f.fill(false)
            f[start] = true
            ds[start] = 0
            while (qh < qt) {
                val i = q[qh++]
                for (j in g[i]) if (!f[j]) {
                    f[j] = true
                    ds[j] = ds[i] + 1
                    q[qt++] = j
                }
            }
        }
        var ans = Long.MAX_VALUE
        fun check(u: Int, v: Int, w: Int) {
            val c = w.toLong() * (d[0][u] + 1 + d[v][n - 1])
            if (c < ans) ans = c
        }
        for (i in 0 until m) {
            check(u[i], v[i], w[i])
            check(v[i], u[i], w[i])
        }
        for (i in 0 until n) for (j in 0 until n) {
            val c = mn[j].toLong() * (d[0][i] + d[i][n - 1] + d[i][j] + 2)
            if (c < ans) ans = c
        }
        println(ans)
    }
}