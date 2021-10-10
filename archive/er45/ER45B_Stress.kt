package er45b

import kotlin.system.*

fun main(args: Array<String>) {
    val n = 200_000
    val time = measureTimeMillis {
        val s = (1..n).map { "1000000" }.joinToString(separator = " ")
        println(solve(s, n, 1))
    }
    println("time = $time")
}