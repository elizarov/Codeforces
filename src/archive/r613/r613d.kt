package archive.r613

import kotlin.math.*

private fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.sorted().toIntArray()

    fun solve(l: Int, r: Int, b: Int): Int {
        if (b < 0) return 0
        var i = l
        val mask = 1 shl b
        while (i < r && (a[i] and mask) == 0) i++
        val x0 = if (l < i) solve(l, i, b - 1) else -1
        val x1 = if (i < r) solve(i, r, b - 1) else -1
        if (x0 < 0) return x1
        if (x1 < 0) return x0
        return min(max(x0, mask or x1), max(mask or x0, x1))
    }

    println(solve(0, n, 30))
}
