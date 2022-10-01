fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val c0 = a.count { it % 2 == 0 }
        val c1 = n - c0
        val dp = Array(2) { Array(2) { Array(c0 + 1) { kotlin.arrayOfNulls<Boolean>(c1 + 1) } } }
        fun solve(m: Int, s: Int, c0: Int, c1: Int): Boolean {
            dp[m][s][c0][c1]?.let { return it }
            if (c0 == 0 && c1 == 0) return s == m
            var res = false
            if (c0 > 0 && !solve(1 - m, s, c0 - 1, c1)) res = true
            if (!res && c1 > 0 && !solve(1 - m, if (m == 0) 1 - s else s, c0, c1 - 1)) res = true
            dp[m][s][c0][c1] = res
            return res
        }
        println(if (solve(0, 0, c0, c1)) "Alice" else "Bob")
    }
}