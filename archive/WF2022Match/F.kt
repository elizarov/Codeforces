import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var ans = -1
        fun check(i: Int, k: Int) {
            var cnt = 0
            for (j in 0 until n) {
                val d = abs(a[i] - a[j])
                if (d % k == 0) cnt++
            }
            if (cnt >= n / 2) ans = max(ans, k)
        }
        val mx = a.groupingBy { it }.eachCount().values.maxOrNull()!!
        if (mx < n / 2) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    val d = abs(a[i] - a[j])
                    if (d == 0) continue
                    check(i, d)
                    var k = 1
                    while (k * k <= d) {
                        if (d % k == 0) {
                            check(i, k)
                            check(i, d / k)
                        }
                        k++
                    }
                }
            }
        }
        println(ans)
    }
}