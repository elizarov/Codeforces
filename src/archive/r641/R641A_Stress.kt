package archive.r641

import kotlin.random.*
import kotlin.system.*

fun main() {
    val n = 100_000
    val rnd = Random(1)
    val a = List(n) { rnd.nextInt(1..200_000)}
    val time = measureTimeMillis {
        solveGcdLcm(n, a)
    }
    println("$time ms")
}