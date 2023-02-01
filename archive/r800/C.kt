private const val inf = Int.MAX_VALUE / 2

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val g = List(n) { ArrayList<Int>() }
    repeat(m) {
        val (v, u) = readln().split(" ").map { it.toInt() - 1 }
        g[v] += u
    }
    val ans = solveC(n, g)
    println(ans)
}

fun solveC(n: Int, g: List<ArrayList<Int>>): Int {
    val dp = IntArray(n) { -1 }
    dp[n - 1] = 0
    val solve = DeepRecursiveFunction<Int, Int> f@{ v ->
        if (dp[v] != -1) return@f dp[v]
        dp[v] = inf
        var ans = inf
        val c = g[v].map { callRecursive(it) }.sorted()
        for (i in c.indices) {
            ans = minOf(ans, c[i] + c.size - i)
        }
        dp[v] = ans
        ans
    }
    return solve(0)
}