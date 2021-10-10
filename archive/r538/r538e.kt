package archive.r538

import kotlin.math.*
import kotlin.random.*

fun main() {
    val n = readLine()!!.toInt()
    val k = min(38, n)
    val rq = IntArray(n) { i -> i + 1 }
    val a = IntArray(k)
    for (i in 0 until k) {
        val j = Random.nextInt(i until n)
        rq[i] = rq[j].also { rq[j] = rq[i] }
        println("? ${rq[i]}")
        a[i] = readLine()!!.toInt()
    }
    a.sort()
    var d = a[1] - a[0]
    for (i in 2 until k) d = gcd(d, a[i] - a[i - 1])
    var l = 0
    var r = n
    while (r - l > 1) {
        val m = (l + r) / 2
        val x = a[0] + (n - 1 - m) * d.toLong()
        val hasAbove = if (x > 1_000_000_000L) false else {
            println("> $x")
            readLine() == "1"
        }
        if (hasAbove) {
            r = m
        } else {
            l = m
        }
    }
    val x1 = a[0] - d * l
    println("! $x1 $d")

}

tailrec fun gcd(x: Int, y: Int): Int =
    if (y == 0) x else gcd(y, x % y)
