package archive.raif1

import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val (x1, y1, x2, y2) = readLine()!!.split(" ").map { it.toLong() }
        val d = (x1 - x2).absoluteValue + (y1 - y2).absoluteValue
        val ans = if (x1 == x2 || y1 == y2) d else d + 2
        println(ans)
    }
}