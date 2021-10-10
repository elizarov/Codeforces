package archive.kh3

fun main() {
    val (m, k) = readLine()!!.split(" ").map { it.toInt() }
    val f = factor(m) ?: run {
        println(-1)
        return
    }
    val dp = HashMap<Long,Long>()
    val dig = IntArray(100_000)
    fun count(nd: Int, p: Long = -1): Long {
        val e = f[0] + (f[1] shl 5) + (f[2] shl 10) + (f[3] shl 15) + (nd.toLong() shl 20)
        if (nd == 0) return if (e == 0L) 1 else 0
        if (p == -1L) dp[e]?.let { return it }
        var cnt = 0L
        for (d in 1..9) {
            dig[nd - 1] = d
            val df = factors[d]
            for (i in 0..3) f[i] -= df[i]
            if (f.all { it >= 0 }) {
                val nc = count(nd - 1)
                if (p >= 0 && cnt + nc > p) return count(nd - 1, p - cnt)
                cnt += nc
            }
            for (i in 0..3) f[i] += df[i]
        }
        dp[e] = cnt
        return cnt
    }
    var num = 1L
    var nd = 1
    while (count(nd) <= k - num) num += count(nd++)
    check(count(nd, k - num) == 1L)
    println(dig.take(nd).reversed().joinToString(""))
}

private val pr = listOf(2, 3, 5, 7)
private val factors = Array(10) { factor(it)!! }

private fun factor(m: Int): IntArray? {
    val f = IntArray(4)
    if (m <= 1) return f
    var rem = m
    for ((i, p) in pr.withIndex()) {
        while (rem % p == 0) {
            rem /= p
            f[i]++
        }
    }
    return f.takeIf { rem == 1 }
}