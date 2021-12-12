import java.lang.Math.abs

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val x = readLine()!!.split(" ").map { it.toInt() }.sorted()
        var i = n - 1
        var ans = 0L
        while(i >= 0 && x[i] >= 0) {
            ans += 2 * x[i]
            i -= k
        }
        i = 0
        while (i < n && x[i] < 0) {
            ans += -2 * x[i]
            i += k
        }
        val m = maxOf(abs(x[0]), abs(x[n - 1]))
        println(ans - m)
    }
}