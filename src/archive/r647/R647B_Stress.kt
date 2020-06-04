package archive.r647

import kotlin.math.*
import kotlin.random.*

fun main() {
    val n = 5
    val p = 3
    val rnd = Random(1)
    repeat(10000) {
        val k = List(n) { rnd.nextInt(0..10) }.sortedDescending()
        val z = k.map { p.toDouble().pow(it).toLong() }
        var best = Long.MAX_VALUE
        for (m in 0 until (1 shl n)) {
            var ans = 0L
            for (i in 0 until n) if (m and (1 shl i) != 0) ans += z[i] else ans -= z[i]
            best = min(best, abs(ans))
        }
        val res = solveB(n, p, k)
        if (res != (best % 1000000007).toInt()) {
            println("$n $p")
            println(k.joinToString(" "))
            println("---")
            println("res=$res")
            println("best=$best")
            return
        }
    }
}