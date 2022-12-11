import kotlin.math.max
import kotlin.math.min

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val a = Array(n) { readln() }
    val u = Array(n) { IntArray(m) }
    val d = Array(n) { IntArray(m) }
    for (j in 0 until m) {
        var k = -1
        for (i in 0 until n) {
            u[i][j] = k
            if (a[i][j] != '.') k = i
        }
        k = n
        for (i in n - 1 downTo 0) {
            d[i][j] = k
            if (a[i][j] != '.') k = i
        }
    }
    var best = 0
    fun check(ul0: Int, dl0: Int, ul1: Int, dl1: Int, hd: Int) {
        val ul = min(ul0, ul1)
        val dl = min(dl0, dl1)
        if (ul > 1 && dl > 1 && hd > 1) {
            val c = 2 * (ul + dl - 1) + hd - 1
            best = max(best, c)
        }
    }
    for (i in 1 until n - 1) {
        for (j in 0 until m - 2) {
            if (a[i][j] == '#') continue
            var mc = 0
            if (a[i][j] == 'm') mc++
            val u0 = u[i][j]
            val ul0 = i - u0
            val d0 = d[i][j]
            val dl0 = d0 - i
            for (k in j + 1 until m) {
                if (a[i][k] == '#') break
                if (a[i][k] == 'm') mc++
                if (mc > 1) break
                val u1 = u[i][k]
                val ul1 = i - u1
                val d1 = d[i][k]
                val dl1 = d1 - i
                check(ul0, dl0, ul1, dl1, k - j)
                if (mc == 0) {
                    if (u0 >= 0 && a[u0][j] == 'm') {
                        check(i - u[u0][j], dl0, ul1, dl1, k - j)
                    }
                    if (d0 < n && a[d0][j] == 'm') {
                        check(ul0, d[d0][j] - i, ul1, dl1, k - j)
                    }
                    if (u1 >= 0 && a[u1][k] == 'm') {
                        check(ul0, dl0, i - u[u1][k], dl1, k - j)
                    }
                    if (d1 < n && a[d1][k] == 'm') {
                        check(ul0, dl0, ul1, d[d1][k] - i, k - j)
                    }
                }
            }
        }
    }
    println(best)
}