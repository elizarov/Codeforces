package archive.kh1

import kotlin.math.*

fun main() {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.split(" ").map { it.toInt() }.sorted()
    var i = 0
    var j = 0
    var sumR = 0L // p ..< j
    while (j < m) sumR += a[j++]
    var p = 0
    var sumL = 0L // i ..< p

    fun movePR() {
        val x = a[p++]
        sumL += x
        sumR -= x
    }

    var ans = Long.MAX_VALUE

    while (true) {
        while (p < i) movePR()
        while (p < j) {
            val x0 = a[p].toLong()
            val pl0 = (p - i) * x0 - sumL
            val pr0 = sumR - (j - p) * x0
            check(pl0 <= k)
            check(pl0 <= pr0)
            ans = min(ans, pl0 + pr0)
            if (p + 1 >= j) break
            val x1 = a[p + 1].toLong()
            val pl1 = (p + 1 - i) * x1 - (sumL + x0)
            val pr1 = (sumR - x0) - (j - p - 1) * x1
            // position = x0 + t
            // pl = pl0 + t * (p - i + 1)
            // pr = pr0 - (j - p - 1) * t
            var t = x1 - x0
            if (pl1 > pr1) {
                // pl0 + t * (p - i + 1) == pr0 - (j - p - 1) * t
                // t * (j - i) == pr0 - pl0
                t = min(t, (pr0 - pl0) / (j - i))
            }
            if (pl1 > k) {
                // pl0 + t * (p - i + 1) == k
                t = min(t, (k - pl0) / (p - i + 1))
            }
            if (t < x1 - x0) {
                val pl2 = pl0 + t * (p - i + 1)
                val pr2 = pr0 - (j - p - 1) * t
                ans = min(ans, pl2 + pr2)
                val pl3 = pl2 + (p - i + 1)
                val pr3 = pr2 - (j - p - 1)
                if (pl3 <= k) ans = min(ans, pl3 + pr3)
                break
            }
            movePR()
        }
        if (j >= n) break
        sumL -= a[i++]
        sumR += a[j++]
    }

    println(ans)
}