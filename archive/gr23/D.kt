@OptIn(ExperimentalStdlibApi::class)
fun main() {
    repeat(readln().toInt()) {
        val (n, k0) = readln().split(" ").map { it.toInt() }
        val p = listOf(-1) + readln().split(" ").map { it.toInt() - 1 }
        val s = readln().split(" ").map { it.toInt() }
        val c = Array(n) { ArrayList<Int>() }
        for (i in 1 until n) c[p[i]] += i
        data class IK(val i: Int, val k: Int)
        val dp = HashMap<IK, Long>()
        val solve = DeepRecursiveFunction<IK, Long> f@{ ik ->
            val (i, k) = ik
            if (k == 0) return@f 0L
            val ci = c[i]
            var ans = s[i].toLong() * k
            if (ci.isEmpty()) return@f ans
            dp[ik]?.let { return@f it }
            val t = k / ci.size
            val m = k % ci.size
            val b = ci.map { j -> callRecursive(IK(j, t)) }
            ans += b.sum()
            if (m > 0) {
                val d = ci.mapIndexed { index, j -> callRecursive(IK(j, t + 1)) - b[index] }
                    .sortedDescending().take(m)
                ans += d.sum()
            }
            dp[ik] = ans
            ans
        }
        println(solve(IK(0, k0)))
    }
}