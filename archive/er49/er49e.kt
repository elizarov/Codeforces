package archive.er49

import kotlin.math.*

private const val MOD = 998244353

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val c = Array(n + 1) { IntArray(n + 1) }
    val d = Array(n + 1) { IntArray(n + 1) }
    c[0][0] = 1
    d[0][0] = 1
    for (x in 1..n) {
        for (m in 1..x) {
            c[x][m] = d[x - m][min(m, x - m)]
            for (l in 1 until m) {
                c[x][m] = add(c[x][m], c[x - l][m])
            }
            d[x][m] = add(c[x][m], d[x][m - 1])
        }
    }
    var sum = 0
    wl@ for (w in 1..n) {
        for (h in 1..n) {
            if (w * h >= k) continue@wl
            sum = add(sum, mul(c[n][w], c[n][h]))
        }
    }
    sum = mul(sum, 2)
    println(sum)
}

private fun add(a: Int, b: Int): Int = ((a.toLong() + b) % MOD).toInt()
private fun mul(a: Int, b: Int): Int = ((a.toLong() * b) % MOD).toInt()
