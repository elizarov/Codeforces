import kotlin.math.absoluteValue

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val p = readln().split(" ").map { it.toInt() }
        var ans = p.sumOf { it.absoluteValue.toLong() }
        val d1 = p.sumOf { (it + 1).absoluteValue.toLong() }
        if (n == 1) {
            ans = (p[0] - p[1]).absoluteValue.toLong()
        }
        if (n % 2 == 0) {
            for (x in p) {
                val d = d1 - (x + 1).absoluteValue + (x - n).absoluteValue
                ans = minOf(ans, d)
            }
        }
        if (n == 2) {
            val d = p.sumOf { (it - 2).absoluteValue.toLong() }
            ans = minOf(ans, d)
        }
        println(ans)
    }
}