import kotlin.math.abs

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        println(solveC(n, a))
    }
}

fun solveC(n: Int, a0: List<Int>): Int {
    var ans = 0
    fun check(a: List<Int>) {
        val a1 = a[0]
        for (j in 2 until n) {
            val cur = abs(a1 - a[j]) + abs(a[j] - a[j - 1])
            ans = maxOf(ans, cur)
        }
    }
    check(a0.sorted())
    check(a0.sortedDescending())
    return ans
}