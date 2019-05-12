package archive.r538

import kotlin.math.*

fun main() {
    val n = readLine()!!.toInt()
    val c = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
    val d = IntArray(n)
    var k = 1
    d[0] = c[0]
    for (i in 1 until n) if (c[i] != c[i - 1]) d[k++] = c[i]

    val dp = IntArray(2 * k * k) { -1}

    fun compute(i: Int, j: Int, lr: Int): Int {
        if (i >= j) return 0
        val p = (i * k + j) * 2 + lr
        if (dp[p] >= 0) return dp[p]
        val a1 = compute(i, j - 1, 0) + when {
            d[i] == d[j] -> 0
            lr == 1 -> 1
            else -> 2
        }
        val a2 = compute(i + 1, j, 1) + when {
            d[i] == d[j] -> 0
            lr == 0 -> 1
            else -> 2
        }
        val a3 = compute(i, j - 1, 1) + when {
            lr == 1 -> 1
            d[i] == d[j] -> 1
            else -> 2
        }
        val a4 = compute(i + 1, j, 0) + when {
            lr == 0 -> 1
            d[i] == d[j] -> 1
            else -> 2
        }
        val a = min(min(a1, a2), min(a3, a4))
        dp[p] = a
        return a
    }

    val ans = min(compute(0, k - 1, 0), compute(0, k - 1, 1))
    println(ans)
}

