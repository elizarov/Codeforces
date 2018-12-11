package archive.r526

import kotlin.system.*

fun main(args: Array<String>) {
    val n = 300_000
    val w = LongArray(n) { 1_000_000_000L }
    val g = Array(n) { ArrayList<E>() }
    repeat(n - 1) { i ->
        addEdge(g, i, i + 1, 1)
    }
    val time = measureTimeMillis {
        println(solveBestPath(n, w, g))
    }
    println("time = $time")
}