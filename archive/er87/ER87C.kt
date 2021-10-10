package archive.er87

import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        println(solveC(n))
    }
}

fun solveC(n: Int): Double {
    val a = PI / n
    if (n % 2 == 0) return 1 / tan(a / 2)
    val d = 1 / sin(a / 2)
    val h = d * cos(a / 4)
    return h
}

