
fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val g = Array(n) { ArrayList<Int>() }
        repeat(n - 1) {
            val (x, y) = readln().split(" ").map { it.toInt() - 1 }
            g[x] += y
            g[y] += x
        }
        println(solveD(n, g))
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun solveD(n: Int, g: Array<ArrayList<Int>>): Int {
    val v = BooleanArray(n)
    data class R(val ans: Int, val up: Boolean)
    val dfs = DeepRecursiveFunction<Int, R> { i ->
        v[i] = true
        var zc = 0
        var kc = 0
        var ans = 0
        var up = false
        for (j in g[i]) {
            if (v[j]) continue
            val (r, u) = callRecursive(j)
            if (r == 0) zc++ else kc++
            if (u) up = true
            ans += r
        }
        if (zc > 1) ans += zc - 1
        if (zc > 0) up = false
        if (((kc == 1 && zc == 0) || (kc > 0 && zc == 1)) && !up) {
            ans++
            up = true
        }
        R(ans, up)
    }
    var (ans, _) = dfs(0)
    if (ans == 0 && n > 1) ans = 1
    return ans
}

/*
exp: 2

1
5
1 4
1 5
2 4
3 4


 */