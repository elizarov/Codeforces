package archive.kh2

import kotlin.math.*

fun main() {
    val (n, a, b, k) = readLine()!!.split(" ").map { it.toInt() }
    val rm = readLine()!!.split(" ").map { it.toInt() }
        .groupingBy { it }.eachCount()
        .mapValues { Left(it.value) }
    val rl = rm.keys.sorted()
        .let { if (a >= b) it else it.reversed() }
    var ans = 0
    for (r in rl) {
        val p = rm.getValue(r)
        if (p.cnt < a) continue
        val q = rm[r * k] ?: continue
        if (q.cnt < b) continue
        val t = min(p.cnt / a, q.cnt / b)
        ans += t
        p.cnt -= a * t
        q.cnt -= b * t
    }
    println(ans)
}

data class Left(var cnt: Int)