fun main() {
    val (n, kk) = readln().split(" ").map { it.toInt() }
    var i0 = 0
    var m = kk
    var prv = IntArray(n + 1)
    var cur = IntArray(n + 1)
    val sum = IntArray(n + 1)
    val ans = IntArray(n + 1)
    val mod = 998244353
    cur[0] = 1
    while (i0 + m <= n) {
        val t = cur
        cur = prv
        prv = t
        cur.fill(0)
        sum.fill(0)
        for (i in i0..n) {
            cur[i] = (cur[i] + sum[i % m]) % mod
            ans[i] = (ans[i] + cur[i]) % mod
            sum[i % m] = (sum[i % m] + prv[i]) % mod
        }
        i0 += m
        m++
    }
    println(ans.drop(1).joinToString(" "))
}