package archive.r706

import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = ArrayList<Int>(n)
        val b = ArrayList<Int>(n)
        repeat(2 * n) {
            val (x, y) = readLine()!!.split(" ").map { it.toInt() }
            when {
                x == 0 -> a += abs(y)
                y == 0 -> b += abs(x)
            }
        }
        a.sort()
        b.sort()
        var sum = 0.0
        for (i in 0 until n)
            sum += sqrt(a[i].toDouble() * a[i] + b[i].toDouble() * b[i])
        println(sum)
    }
}