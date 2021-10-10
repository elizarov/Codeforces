package archive.r488

import java.util.*
import kotlin.system.*

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val n = 60
        val rnd = Random(1)
        val y1a = IntArray(n) { rnd.nextInt(100000) }
        val y1b = IntArray(n) { rnd.nextInt(100000) }
        println(solveShips(n, n, y1a, y1b))
    }
    println("time = $time")
}