@OptIn(ExperimentalStdlibApi::class)
fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val g = List(n) { ArrayList<Int>() }
        repeat(n - 1) {
            val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
            g[u] += v
            g[v] += u
        }
        val d = IntArray(n) { -1 }
        val p = IntArray(n) { -1 }
        val dfs = DeepRecursiveFunction<Int, Unit> { u ->
            for (v in g[u]) {
                if (d[v] < 0) {
                    d[v] = d[u] + 1
                    p[v] = u
                    callRecursive(v)
                }
            }
        }
        d[0] = 0
        dfs(0)
        val m = n.bs
        val rem = Array(m + 1) { ArrayList<Int>() }
        for (i in 1..n) rem[i.bs].add(i)
        val ans = IntArray(n)
        d.withIndex().sortedByDescending { it.value }.forEach { (u, _) ->
            var min = Int.MAX_VALUE
            var max = 0
            for (v in g[u]) {
                if (v == p[u]) continue
                min = minOf(min, ans[v])
                max = maxOf(max, ans[v])
            }
            val a = min.bs
            val b = max.bs
            var k = m
            while (rem[k].isEmpty() || k in a..b) k--
            ans[u] = rem[k].removeLast()
        }
        println(ans.joinToString(" "))
    }
}

val Int.bs get() = 32 - countLeadingZeroBits()