import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    when (solveTr(n, a)) {
        0 -> println("Petr")
        1 -> println("Um_nik")
    }
}

fun solveTr(n: Int, a: IntArray): Int {
    val bp = (n - 2).toDouble() / n
    var c = 0
    for (i in 0 until n) {
        if (a[i] == i + 1) c++
    }
    val est = log(c.toDouble() / n, bp)
    println("c = $c, est = $est")
    return if (est <= 5 * n) 0 else 1
}