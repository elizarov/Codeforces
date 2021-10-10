package archive.r641

import java.util.*
import kotlin.system.*

fun main() {
    val n = 1000
    val m = 1000
    val a = BitSet(n * m)
    for (i in 0 until n) for (j in 0 until m) if ((i + j) % 2 != 0) a.set(i * m + j)
    a.set(0)
    val l = ArrayList<BitSet>()
    val time = measureTimeMillis {
        simGames(n, m, a, l) + 1
    }
    println("$time ms; ${l.size} steps")
}