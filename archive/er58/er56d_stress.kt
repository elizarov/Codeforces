package archive.er58

import kotlin.system.*

fun main() {
    val n = 200_000
    val m = 200_000
    val a = IntArray(n) { m }
    val g = Array(n) { ArrayList<Int>() }
    for (x in 0..n - 2) {
        val y = x + 1
        g[x].add(y)
        g[y].add(x)
    }
    val time = measureTimeMillis {
        println(solveTree(a, g))
    }
    println("time = $time")
}