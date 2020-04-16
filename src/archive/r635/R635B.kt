package archive.r635

import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val (nr, ng, nb) = readLine()!!.split(" ").map { it.toInt() }
        val c = Array(3) {
            readLine()!!.split(" ").map { it.toInt() }.distinct().sorted().toIntArray()
        }
        var ans = Long.MAX_VALUE
        for (i in 0..2) {
            for (j in 0..2) if (j != i) {
                val k = 3 - i - j
                ans = minOf(ans, solve(c[i], c[j], c[k]))
            }
        }
        println(ans)
    }
}

fun solve(a: IntArray, b: IntArray, c: IntArray): Long {
    var i  = 0
    var j = 0
    var ans = Long.MAX_VALUE
    for (x in a) {
        while (b[i] < x) {
            i++
            if (i >= b.size) return ans
        }
        while (c[j] < x) {
            j++
            if (j >= c.size) return ans
        }
        while (b[i] < c[j]) {
            i++
            if (i >= b.size) return ans
        }
        val y = b[i]
        val mid2 = (x + y).toLong()
        while (j + 1 < c.size && abs(mid2 - 2 * c[j + 1]) < abs(mid2 - 2 * c[j])) {
            j++
        }
        val z = c[j]
        val bal = d2(x, y) + d2(x, z) + d2(y, z)
        ans = minOf(ans, bal)
    }
    return ans
}

fun d2(x: Int, y: Int): Long = (x - y).toLong() * (x - y)