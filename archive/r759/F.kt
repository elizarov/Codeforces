fun main() {
    val MOD = 998244353L
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val c = LongArray(n + 1)
    val s = LongArray(n + 1)
    val pi = IntArray(n + 1)
    val pv = IntArray(n + 1)
    c[0] = 1
    s[0] = 1
    pv[0] = -1
    pi[0] = -1
    var pc = 0
    for (i in 0 until n) {
        c[i + 1] = c[i] * a[i] % MOD
        while (pc >= 0 && a[i] < pv[pc]) pc--
        val j = pi[pc] + 1
        pc++
        pv[pc] = a[i]
        pi[pc] = i
        val sgn = if ((i - j) % 2 != 0) 1 else -1
        val ss = (s[i] + sgn * s[j] + MOD) % MOD
        c[i + 1] = (c[i + 1] - ss * a[i] % MOD + MOD) % MOD
        c[i + 1] = (c[i + 1] + sgn * c[j] + MOD) % MOD
        s[i + 1] = (c[i + 1] - c[i] + MOD) % MOD
    }
    println(c[n])
}