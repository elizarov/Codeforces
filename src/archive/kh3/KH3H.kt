package archive.kh3

fun main() {
    class Ans(val a: String, val m: String)
    repeat(readLine()!!.toInt()) {
        val s = readLine()!!
        val n = s.length
        val dp = Array(n) { i -> arrayOfNulls<Ans>(i + 1) }
        dp[0][0] = Ans(s.substring(0, 1), "R")
        var best = Ans(s, "")
        fun updateBest(p: Ans) {
            if (p.a <= best.a) best = p
        }
        fun updateDP(i: Int, j: Int, p: Ans) {
            val cur = dp[i][j]
            if (cur == null || p.a < cur.a) dp[i][j] = p
        }
        for (i in 1 until n) {
            for (j in 0 until i) {
                val p = dp[i - 1][j] ?: continue
                updateDP(i, j, Ans(p.a + s[i], p.m + "R"))
                if (j >= i - j) continue
                if (s[i] == p.a[j]) updateDP(i, j + 1, Ans(p.a, p.m + "B"))
                if (s[i] < p.a[j]) updateBest(Ans(p.a, p.m + "B".repeat(n - i)))
            }
        }
        for (p in dp[n - 1]) if (p != null) updateBest(p)
        println(best.m)
    }
}
