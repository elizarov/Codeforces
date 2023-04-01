import algo.timeProcess
import kotlin.random.Random
import kotlin.random.nextInt

fun main() = timeProcess {
    val rnd = Random(1)
    val n = 100000
    val c = 9
    val d = 3
    val a = List(n) { rnd.nextInt(1..1_000_000_000)}
    println(solveC(n, c, d, a))
}