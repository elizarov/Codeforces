import kotlin.math.abs

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = IntArray(101)
        repeat(n) {
            val (w, h) = readln().split(" ").map { it.toInt() }
            for (i in 0..<w) a[i] = maxOf(a[i], h)
        }
        var sum = 0
        var prev = 0
        for (i in 0..<101) {
            if (a[i] == 0) {
                sum += prev
                break
            }
            sum += 2 + abs(prev - a[i])
            prev = a[i]
        }
        println(sum)
    }
}