import algo.timeProcess
import kotlin.random.Random
import kotlin.random.nextInt

fun main() = timeProcess {
    val n = 200_000
    val m = 200_000
    val rnd = Random(1)
    val g = GraphW(n, 2 * m)
    repeat(m) {
        val u = rnd.nextInt(0 until n)
        val v = rnd.nextInt(0 until n)
        val w = rnd.nextInt(1..1000000000)
        g.add(u, v, w)
        g.add(v, u, -w)
    }
    println(solveE(n, g))
}