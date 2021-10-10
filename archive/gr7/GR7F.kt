package archive.gr7

fun main() {
    val n = readLine()!!.toInt()
    val g = IntArray(n) {
        readLine()!!.map { it - '0' }.foldIndexed(0) { i, acc, bit -> acc or (bit shl i) }
    }
    println(solveWise(n, g).joinToString(" "))
}

fun solveWise(n: Int, g: IntArray): LongArray {
    val all = (1 shl n) - 1
    val dp = Array(all) { HashMap<Int, LongArray>() }
    fun count(i: Int, used: Int, next: Int): LongArray {
        if (next == 0) return ZERO
        if (i == n - 1) return ONE
        dp[used][next]?.let { return it }
        val c = LongArray(1 shl (n - i - 1))
        val ofs = 1 shl (n - i - 2)
        val mask = used or (next xor all)
        for (j in 0 until n) {
            if ((1 shl j) and mask != 0) continue
            val used2 = used or (1 shl j)
            val rem2 = used2 xor all
            val c0 = count(i + 1, used2, rem2 and (g[j] xor all))
            if (c0 !== ZERO) for (k in 0 until ofs) c[k] += c0[k]
            val c1 = count(i + 1, used2, rem2 and g[j])
            if (c1 !== ZERO) for (k in 0 until ofs) c[k + ofs] += c1[k]
        }
        dp[used][next] = c
        return c
    }
    return count(0, 0, all)
}

private val ZERO = longArrayOf()
private val ONE = longArrayOf(1)