package archive.er67

import kotlin.math.*

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        solveToys()
    }
}

private fun solveToys() {
    val (n, s, t) = readLine()!!.split(" ").map { it.toInt() }
    val mr = max(n - s, n - t)
    println(mr + 1)
}
