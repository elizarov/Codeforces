package archive.raif1

import kotlin.random.*

fun main() {
    while(true) {
        val n = Random.nextInt(1..2)
        val k = Random.nextInt(n + 1..6)
        val a = List(n) { Random.nextInt(1..10) }.sortedDescending()
        if (a.sum() < k) continue
        val s1 = solveE(n, k, a)
        val s2 = solveOpt(n, k, a)
        if (s1 != s2) {
            println("$n $k")
            println(a.joinToString(" "))
            println("s1 = $s1")
            println("s2 = $s2")
            return
        }
    }
}

private fun solveOpt(n: Int, k: Int, a: List<Int>): Long {
    fun find(i: Int, cur: Int, sum: Long): Long {
        if (i == n) {
            check(cur == k)
            return sum
        }
        val rem = k - cur
        var best = Long.MAX_VALUE
        val from = if (i == n - 1) rem + 1 else 1
        for (c in from..rem + 1) {
            val d = a[i] / c
            if (d == 0) break
            val e = a[i] % c
            var ss = sum
            for (j in 0 until c) {
                ss += sq(if (j < e) d + 1 else d)
            }
            best = minOf(best, find(i + 1, cur + c - 1, ss))
        }
        return best
    }
    return find(0, n, 0L)
}
