package er46

private const val MOD = 998244353

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val d = Array(n + 1) { IntArray(n + 1) }
    for (i in n - 1 downTo 0) {
        val r = n - i
        for (k in r downTo 0) {
            var sum = d[i + 1][k]
            when (k) {
                0 -> {
                    val c = a[i]
                    if (c in 1..n)
                        sum = (sum + d[i + 1][c]) % MOD
                }
                1 -> {
                    sum = (sum + 1 + d[i + 1][0]) % MOD
                }
                else -> {
                    sum = (sum + d[i + 1][k - 1]) % MOD
                }
            }
            d[i][k] = sum
        }
    }
    println(d[0][0])
}