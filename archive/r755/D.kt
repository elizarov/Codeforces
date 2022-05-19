fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val s = List(n) { readLine()!! }
        val a0 = IntArray(n)
        val ans = SolverD(n, s).solve(a0)
        println(ans.length)
        println(ans)
    }
}

class SolverD(val n: Int, val s: List<String>) {
    val dp = HashMap<Cut, String>()
    val s0 = s[0]

    fun solve(a: IntArray): String {
        val i = a[0]
        if (i >= s0.length) return ""
        val cut = Cut(a)
        dp[cut]?.let { return it }
        val a2 = a.copyOf()
        a2[0] = i + 1
        var ans = solve(a2)
        val c = s0[i]
        var found = true
        for (j in 1 until n) {
            val sj = s[j]
            found = false
            for (k in a[j] until sj.length) if (sj[k] == c) {
                a2[j] = k + 1
                found = true
                break
            }
            if (!found) break
        }
        if (found) {
            val ans2 = solve(a2)
            if (ans2.length + 1 > ans.length) ans = c + ans2
        }
        dp[cut] = ans
        return ans
    }
}

class Cut(val a: IntArray) {
    override fun equals(other: Any?): Boolean = other is Cut && other.a.contentEquals(a)
    override fun hashCode(): Int = a.contentHashCode()
    override fun toString(): String = a.contentToString()
}