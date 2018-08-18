package archive.r503

import java.lang.Math.*
import java.util.*

fun main(args: Array<String>) {
    val n = 20
    val a = IntArray(n + 1)
    a[1] = 10
    val seed = 12L
    for (seed in 1L..10000L) {
        println("seed = $seed")
        val rnd = Random(seed)
        for (i in 2..n) {
            val d = a[i - 1] - a[1]
            a[i] = if (abs(d) >= n + 2 - i) {
                a[i - 1] + if (d > 0) -1 else 1
            } else {
                a[i - 1] + if (rnd.nextBoolean()) 1 else -1
            }
        }
//        println(a.toList().subList(1, n + 1).joinToString(" "))
        val res = solveInt(n) { i ->
            Res(i, a[i]).also { println(it) }
        }
        println(res)
        if (res > 0) {
            println("${a[res]} == ${a[(res + n / 2 - 1) % n + 1]}")
        }
    }
}