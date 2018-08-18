package r845

import kotlin.system.*

fun main(args: Array<String>) {
    val s = "15".padEnd(1_000_001, '0')
    val time = measureTimeMillis {
        println(solvePow(s))
    }
    println("time = $time")
}