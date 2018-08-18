package archive.r491

import kotlin.math.max

fun main(args: Array<String>) {
    val b = Array(2) { readLine()!!.toCharArray() }
    val n = b[0].size
    val d = Array(n) { IntArray(3 ) }
    for (i in 1 until n) {
        val best = d[i - 1].max()!!
        for (p in 0..2) d[i][p] = best
        if (b[0][i - 1] == '0' && b[1][i - 1] == '0') {
            for (p in 0..2) {
                for (j in 0..1) if (b[j][i] == '0' && (p and (1 shl j) == 0)) {
                    d[i][p] = max(d[i][p], (if (i > 1) d[i - 2][0] else 0) + 1)
                }
            }
        }
        if (b[0][i] == '0' && b[1][i] == '0') {
            for (j in 0..1) {
                if (b[j][i - 1] == '0')
                    d[i][0] = max(d[i][0], d[i - 1][1 shl j] + 1)
            }
        }
    }
    println(d[n - 1][0])
}