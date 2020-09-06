package archive.gr9

import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val b = a.mapIndexed { i, x ->
            x.absoluteValue * (i % 2 * 2 - 1)
        }
        println(b.joinToString(" "))
    }
}