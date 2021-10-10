package archive.gr9

fun main() {
    val n = 9
    val a = MutableList(n) { 0 }
    fun test() {
        val s = solveC(n, a)
        val c = solveCBrute(a)
        if (s != c) {
            println("Expected: $c")
            println(a.joinToString(" "))
            error("!!!")
        }
    }
    val u = HashSet<Int>()
    fun find(i: Int) {
        if (i == n) {
            test()
            return
        }
        for (j in 1..n) {
            if (j !in u) {
                u += j
                a[i] = j
                find(i + 1)
                u -= j
            }
        }
    }
    find(0)
}

fun solveCBrute(a: List<Int>): Boolean {
    val dp = HashMap<List<Int>, Boolean>()
    fun sol(a: List<Int>): Boolean {
        val n = a.size
        if (n == 1) return true
        dp[a]?.let { return it }
        for (i in 0 until n - 1) if (a[i] < a[i + 1]) {
            if (sol(a.subList(0, i) + a.subList(i + 1, n)) ||
                sol(a.subList(0, i + 1) + a.subList(i + 2, n)))
            {
                dp[a] = true
                return true
            }
        }
        dp[a] = false
        return false
    }
    return sol(a)
}
