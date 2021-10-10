package archive.er49

import java.util.*
import kotlin.system.*

fun main(args: Array<String>) {
    val rnd = Random(1)
    val n = 1_000_000
    val k = 3_000_000
    val d = IntArray(k) { rnd.nextInt(1_000_000_000) + 1 }
    val a = IntArray(n)
    val b = IntArray(n)
    for (i in 0 until n) {
        a[i] = d[rnd.nextInt(k)]
        b[i] = d[rnd.nextInt(k)]
    }
    val time = measureTimeMillis {
        println(solveSession(n, a, b))
    }
    println("time = $time ms")
}