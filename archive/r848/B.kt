import java.lang.Math.*

fun main() {
    repeat(readln().toInt()) {
        val (n, m, d) = readln().split(" ").map { it.toInt() }
        val p = readln().split(" ").map { it.toInt() - 1 }
        val a = readln().split(" ").map { it.toInt() - 1 }
        val pos =  IntArray(n)
        for (i in 0 until n) pos[p[i]] = i
        val notGood = (0 until m - 1).all { i ->
            val p0 = pos[a[i]]
            val p1 = pos[a[i + 1]]
            p0 < p1 && p1 <= p0 + d
        }
        if (notGood) {
            val ans = (0 until m - 1).minOf { i ->
                val p0 = pos[a[i]]
                val p1 = pos[a[i + 1]]
                minOf(p1 - p0, (p0 + d - p1 + 1).takeIf { n - 1 > d } ?: Int.MAX_VALUE)
            }
            println(ans)
        } else {
            println(0)
        }
    }
}