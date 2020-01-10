package archive.r613

import kotlin.math.*

/**
 * @author Roman Elizarov.
 */

fun main() {
    val x = readLine()!!.toLong()
    var rem = x
    val c = IntArray(100)
    val d = LongArray(100)
    var n = 0
    var dc = 2L
    while (dc * dc <= rem) {
        if (rem % dc == 0L) {
            d[n] = dc
            do {
                c[n]++
                rem /= dc
            } while (rem % dc == 0L)
            n++
        }
        dc++
        if (dc and 1 == 0L) dc++
    }
    if (rem != 1L) {
        d[n] = rem
        c[n] = 1
        n++
    }

    fun find(a: Long, i: Int): Long {
        if (i >= n) return a
        var best = find(a, i + 1)
        var bestM = max(best, x / best)
        var aa = a
        for (k in 1..c[i]) {
            aa *= d[i]
        }
        val cur = find(aa, i + 1)
        val curM = max(cur, x / cur)
        if (curM < bestM) {
            best = cur
            bestM = curM
        }
        return best
    }

    val a = find(1L, 0)
    val b = x / a
    println("$a $b")
}
