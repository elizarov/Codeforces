package archive.r706

import java.io.File
import java.io.PrintWriter
import kotlin.random.Random

fun main() {
    File("d_input.txt").printWriter().use {
        it.run {
            genLinearTree()
        }
    }
}

private fun PrintWriter.genLinearTree() {
    val n = 400
    val d = Array(n) { IntArray(n) { Int.MAX_VALUE / 2 } }
    for (i in 0 until n) {
        d[i][i] = 0
    }
    println("$n ${n - 1}")
    val rnd = Random(1)
    repeat(n - 1) {
        val a = it
        val b = it + 1
        d[a][b] = 1
        d[b][a] = 1
        println("${a + 1} ${b + 1}")
    }
}

private fun PrintWriter.genRandom() {
    val n = 400
    val m = 600
    val d = Array(n) { IntArray(n) { Int.MAX_VALUE / 2 } }
    for (i in 0 until n) {
        d[i][i] = 0
    }
    println("$n $m")
    val rnd = Random(1)
    for (i in 0 until n) {
        val a = i
        val b = (i + 1) % n
        d[a][b] = 1
        d[b][a] = 1
        println("${a + 1} ${b + 1}")
    }
    repeat(m - n) {
        var a: Int
        var b: Int
        do {
            a = rnd.nextInt(0, n - 1)
            b = rnd.nextInt(a + 1, n)
        } while (d[a][b] == 1)
        d[a][b] = 1
        d[b][a] = 1
        println("${a + 1} ${b + 1}")
    }
}