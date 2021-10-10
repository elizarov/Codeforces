package archive.r554

import java.io.*

fun main() = bufferOut { readSolveWrite() }

private const val MOD = 1000_000_007

private val dp = IntArray(2001 * 2001 * 2) { -1 }

fun compute(b: Int, r: Int, u: Int): Int {
    if (b < 0 || b > r || r == 0) return 0
    val index = (b * 2001 + r) * 2 + u
    dp[index].let { if (it >= 0) return it }
    var res = (compute(b + 1, r - 1, 0) + compute(b - 1, r - 1, 0)) % MOD
    if (u == 0) {
        if (b < r)
            res =  modMax(res, (1 + compute(b + 1, r - 1, 1) + compute(b - 1, r - 1, 0)) % MOD)
        if (b > 0)
            res = modMax(res, (1 + compute(b + 1, r - 1, 0) + compute(b - 1, r - 1, 1)) % MOD)
    }
    dp[index] = res
    return res
}

fun modMax(a: Int, b: Int): Int =
    if ((a - b + MOD) % MOD < MOD / 2) a else b

private fun PrintWriter.readSolveWrite() {
    val n = readLine()!!.toInt()
    println(compute(0, 2 * n, 0))
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
