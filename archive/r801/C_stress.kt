import kotlin.random.*
import kotlin.time.*

@OptIn(ExperimentalTime::class)
fun main() {
    val n = 100
    val m = 100
    val rnd = Random(1)
    val a = List(n) { List(m) { if (rnd.nextBoolean()) 1 else -1 } }
    val time = measureTime { solveC(n, m, a) }
    println(time)
}