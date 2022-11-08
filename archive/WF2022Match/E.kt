import kotlin.math.*

fun main() {
    val (n, h, l, r) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }
    val dp = Array(n + 1) { IntArray(h) { -1 } }
    dp[0][0] = 0
    for (i in 0 until n) {
        for (t0 in 0 until h) {
            val b = dp[i][t0]
            if (b < 0) continue
            for (d in -1..0) {
                val t1 = (t0 + a[i] + d) % h
                val inc = if (t1 in l..r) 1 else 0
                dp[i + 1][t1] = max(dp[i + 1][t1], b + inc)
            }
        }
    }
    var ans = 0
    for (t in 0 until h) ans = max(ans, dp[n][t])
    println(ans)
}