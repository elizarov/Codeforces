package er45e

import kotlin.system.*

fun main(args: Array<String>) {
    val n = 1_000_000
    val m = 999600
    val k = n
    val s = IntArray(m) { 2 + it }
    val a = IntArray(k) { 1 }

    val time = measureTimeMillis {
        println(solveLamps(n, m, k, s, a))
    }
    println("time = $time")
}