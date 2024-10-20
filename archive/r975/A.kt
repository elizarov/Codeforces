fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toLong() }
        val a = readln().split(" ").map { it.toLong() }
        var ans = 1L
        val sum = a.sum()
        val x = a.max()
        for (s in 2L..n) {
            val ok = if (sum <= x * s) {
                x * s - sum <= k
            } else {
                val next = (sum + s - 1) / s * s
                next - sum <= k
            }
            if (ok) ans = s
        }
        println(ans)
    }
}