val MOD = 998244353L

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        println(solveD(n, a))
    }
}

fun solveD(n: Int, a: List<Int>): Long {
    val c = LongArray(n + 4)
    val b = LongArray(n + 4) { 1L }
    val r = LongArray(n + 4)
    for (i in n - 1 downTo 0) {
        if (a[i] >= 2) r[i] = b[a[i] - 2] * b[a[i]] % MOD
        b[a[i]] = (b[a[i]] * 2) % MOD
    }
    var ans = (b[1] + MOD - 1) % MOD
    for (i in 0 until n) {
        val x = (if (a[i] == 0) c[0] + 1 else (c[a[i]] + c[a[i] - 1])) % MOD
        c[a[i]] = (c[a[i]] + x) % MOD
        ans = (ans + x) % MOD
        if (a[i] >= 2) {
            ans = (ans + c[a[i] - 2] * r[i]) % MOD
        }
    }
    return ans
}