package archive.gr7

import kotlin.random.*
import kotlin.system.*

fun main() {
    val rnd = Random(1)
    val n = 14
    val g = IntArray(n)
    fun gSet(i: Int, j: Int) {
        g[i] = g[i] or (1 shl j)
    }
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (rnd.nextBoolean()) {
                gSet(i, j)
                gSet(j, i)
            }
        }
    }
    val time = measureTimeMillis {
        solveWise(n, g)
    }
    println("n = $n; time = $time ms")
}