import kotlin.system.*

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val n = 100_000
        val g0 = List(n) { ArrayList<Int>() }
        for (i in 0 until n) {
            val j = (i + 1) % n
            g0[i] += j
            g0[j] += i
        }
        val s = 100
        val k = 100
        val a = IntArray(n)
        for (i in 1 until n) {
            a[i] = 1 + i % (k - 1)
        }
        println(solvePow(n, k, s, a, g0).joinToString(" "))
    }
    println("Time: $time")
}