package er45a

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m, a, b) = readLine()!!.split(" ").map { it.toLong() }
    val d = n % m
    val u = m - d
    val pu = u * a
    val pd = d * b
    println(min(pu, pd))
}