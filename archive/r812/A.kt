import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val d = IntArray(4)
        repeat(n) { i ->
            val (x, y) = readln().split(" ").map { it.toInt() }
            val di = abs(x) + abs(y)
            val j = when {
                x > 0 -> 0
                y > 0 -> 1
                x < 0 -> 2
                else -> 3
            }
            d[j] = maxOf(d[j], di)
        }
        println(2 * d.sum())
    }
}