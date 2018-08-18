package archive.r488

import java.util.*
import kotlin.system.*

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val n = 200_000
        val rnd = Random(1)
        val x = 500_000_000
        val a = IntArray(n) { rnd.nextInt(2 * x) }
        val c = solveOrder(n, a, x)
    }
    println("time = $time")
}