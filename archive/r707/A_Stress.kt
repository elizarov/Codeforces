package archive.r707

import kotlin.time.*

fun main() {
    val time = measureTime {
        val n = 200_000
        val a = List(n) { 1 }
        solveA(n, a)
    }
    println("time = ${time.inSeconds} sec")
}