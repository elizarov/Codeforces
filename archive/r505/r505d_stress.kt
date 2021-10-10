package archive.r505

import kotlin.system.*

fun main(args: Array<String>) {
    val n = 700
    val a = IntArray(n) { it + 3 }
    val time = measureTimeMillis {
        println(recoverBst(n, a))
    }
    println("time = $time ms")
}