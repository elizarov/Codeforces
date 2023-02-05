import kotlin.random.*

fun main() {
    val rnd = Random
    val n = 6
    val m = 7
    repeat(Int.MAX_VALUE) { rep ->
        if (rep % 1000 == 0) println(".")
        val g = List(n) { ArrayList<Int>() }
        data class E(val v: Int, val u: Int)
        val es = ArrayList<E>()
        for (i in 0..n - 2) {
            g[i] += i + 1
            es += E(i, i + 1)
        }
        repeat(m - (n - 1)) {
            val v = rnd.nextInt(n)
            val u = rnd.nextInt(n)
            g[v] += u
            es += E(v, u)
        }
        val sol = solveC(n, g)
        val ans = bruteC(n, g)
        if (sol != ans) {
            println("sol = $sol")
            println("ans = $ans")
            println("$n $m")
            es.forEach { e -> println("${e.v + 1} ${e.u + 1}") }
            return
        }
    }
}

private const val inf = Int.MAX_VALUE / 2

fun bruteC(n: Int, g: List<ArrayList<Int>>): Int {
    val f = BooleanArray(n)
    fun solve(v: Int): Int {
        if (v == n - 1) return 0
        if (f[v]) return inf
        f[v] = true
        var ans = inf
        val c = g[v].map { solve(it) }.sorted()
        for (i in c.indices) {
            ans = minOf(ans, c[i] + c.size - i)
        }
        f[v] = false
        return ans
    }

    return solve(0)
}