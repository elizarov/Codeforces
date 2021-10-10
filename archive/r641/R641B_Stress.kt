package archive.r641

import kotlin.random.*

fun main() {
    val n = 6
    val rnd = Random
    repeat(100_000) { attempt ->
        if (attempt % 100 == 0) println()
        print(".")
        val a = IntArray(n) { rnd.nextInt(1..n) }
        val k = rnd.nextInt(1..n)
        val ans = solveMedian(n, k, a)
        val exp = solveMedianBrute(n, k, a)
        if (ans != exp) {
            println()
            println(1)
            println("$n $k")
            println(a.joinToString(" "))
            println("Expected $exp; found $ans")
            return
        }
    }
}

private enum class Mark { YES, NO, PENDING }

fun solveMedianBrute(n: Int, k: Int, a: IntArray): Boolean {
    val dp = HashMap<List<Int>, Mark>()
    dp[List(n) { k }] = Mark.YES
    fun find(a: List<Int>): Mark {
        dp[a]?.let { return it }
        dp[a] = Mark.PENDING
        var ans = Mark.NO
        loop@for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                val b = a.toMutableList()
                b.subList(i, j + 1).sort()
                val m = b[(i + j) / 2]
                var upd = false
                for (t in i..j) {
                    if (b[t] != m) {
                        upd = true
                        b[t] = m
                    }
                }
                if (upd && find(b) == Mark.YES) {
                    ans = Mark.YES
                    break@loop
                }
            }
        }
        dp[a] = ans
        return ans
    }
    return find(a.toList()) == Mark.YES
}
