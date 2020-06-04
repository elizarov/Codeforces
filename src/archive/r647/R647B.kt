package archive.r647

fun main() = System.`in`.bufferedReader().run {
    val ans = IntArray(readLine()!!.toInt()) {
        val (n, p) = readLine()!!.split(" ").map { it.toInt() }
        val k = readLine()!!.split(" ").map { it.toInt() }.sortedDescending()
        solveB(n, p, k) % MOD
    }
    println(ans.joinToString("\n"))
}

fun solveB(n: Int, p: Int, k: List<Int>): Int {
    if (p == 1) return n % 2
    var ans = 0
    var prevK = -1
    var i = 0
    while (i < n) {
        val curK = k[i]
        val i0 = i
        while (i < n && k[i] == curK) i++
        if (ans != 0) ans = pow(ans, p, prevK - curK)
        val cnt = i - i0
        ans = when {
            ans < cnt -> (cnt - ans) % 2
            ans < MOD -> ans - cnt
            else -> MOD + (ans - cnt) % MOD
        }
        prevK = curK
    }
    ans = pow(ans, p, prevK)
    return ans
}

private val MOD = 1000000007

private fun mul(a: Int, b: Int): Int {
    val m = a.toLong() * b
    if (a < MOD && b < MOD && m < MOD) return m.toInt()
    return MOD + (m % MOD).toInt()
}

private fun pow(acc: Int, a: Int, b: Int): Int {
    var cur = acc
    var pow = a
    var n = b
    while (n > 0) {
        if (n % 2 == 1) cur = mul(cur, pow)
        pow = mul(pow, pow)
        n /= 2
    }
    return cur
}
