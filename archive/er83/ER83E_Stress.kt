package archive.er83

import kotlin.system.*

fun main() {
    val n = 500
    val a = IntArray(n) { it }
    a[0] = 1
    val time = measureTimeMillis {
        val ans = solveCompress(n, a)
        println("ans = $ans")
    }
    println("time = $time ms")
}