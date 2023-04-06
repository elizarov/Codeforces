import algo.timeProcess
import kotlin.random.Random

fun main() = timeProcess {
    val n = 100_000
    val g = Graph(n, 2 * n - 2)
    val rnd = Random(1)
    val sh = (0 until n).shuffled(rnd)
    for (i in 1 until n) {
        g.add(sh[i - 1], sh[i])
        g.add(sh[i], sh[i - 1])
    }
    val a = IntArray(n)
    for (i in 0 until n / 2) {
        val w = n - i
        a[sh[i]] = w
        a[sh[n - i - 1]] = w
    }
    val ans = solveE(n, g, a)
    println(ans.take(10).joinToString(" "))
}