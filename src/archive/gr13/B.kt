import java.lang.Math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, u, v) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }
        println(solveB(n, u, v, a))
    }
}

fun solveB(n: Int, u: Int, v: Int, a: List<Int>): Int {
    val minA = a.minOrNull()!!
    val maxA = a.maxOrNull()!!
    if (maxA - minA + 1 > n) return 0
    if (minA == maxA) return minOf(u + v, 2 * v)
    for (i in 0 until n - 1) {
        if (abs(a[i] - a[i + 1]) > 1) return 0
    }
    return minOf(u, v)
}
