package archive.r707

import kotlin.random.*
import kotlin.time.*

fun main() {
    val time = measureTime {
        val n = 1500
        val m = 1500
        val rnd = Random(1)
        val a = List(n) { j -> List(m) { i -> rnd.nextInt(2) } }
        val b = a.sortedWith { x, y -> cmp(x, y) }
        val sol = solveC(n, m, a, b)
        println(sol?.size ?: -1)
    }
    println("time = ${time.inSeconds}")
}

fun cmp(x: List<Int>, y: List<Int>): Int {
    for (i in x.indices) {
        val r = x[i].compareTo(y[i])
        if (r != 0) return r
    }
    return 0
}
