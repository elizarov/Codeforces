package er45c

import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = Array(n) { analyzeStr(readLine()!!) }
    val bg = a.filter { it.d >= it.b }.groupingBy { it.b }.eachCount()
    val res = a.map { fst ->
        if (fst.d < 0) 0L else {
            bg[-fst.b]?.toLong() ?: 0L
        }
    }.sum()
    println(res)
}

class BD(val b: Int, val d: Int)

fun analyzeStr(s: String): BD {
    var b = 0
    var d = 0
    for (c in s) {
        when (c) {
            '(' -> b++
            ')' -> {
                b--
                d = min(d, b)
            }
            else -> error("Invalid $c")
        }
    }
    return BD(b, d)
}                        