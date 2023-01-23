fun main() {
    val MOD = 1000000007L
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val g = List(n) { ArrayList<Int>() }
        repeat(n - 1) {
            val (u, v) = readln().split(" ").map { it.toInt() - 1 }
            g[u] += v
            g[v] += u
        }
        var ans = 0L
        val f = BooleanArray(n)
        val dfs = DeepRecursiveFunction<Int, Int> { u ->
            f[u] = true
            var d = 0
            for (v in g[u]) if (!f[v]) {
               d = maxOf(d, callRecursive((v)))
            }
            d++
            ans = (ans + d) % MOD
            d
        }
        dfs(0)
        repeat(n - 1) {
            ans = (ans * 2) % MOD
        }
        println(ans)
    }
}