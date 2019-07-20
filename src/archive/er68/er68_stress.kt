package archive.er68

import kotlin.system.*

private const val MOD = 1_000_000_007

fun main() {
    val time = measureTimeMillis {
        val m = 200_000
        var bin0 = IntArray(m + 1)
        var bin1 = IntArray(m + 1)
        bin0[0] = 1
        for (n in 1..m) {
            bin1[0] = 1
            for (k in 1 until n) {
                bin1[k] = (bin0[k] + bin0[k - 1]) % MOD
            }
            bin1[n] = 1
            bin0 = bin1.also { bin1 = bin0 }
        }
    }
    println("$time ms")
}