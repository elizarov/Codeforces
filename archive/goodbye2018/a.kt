package archive.goodbye2018

fun main() {
    val (y, b, r) = readLine()!!.split(" ").map { it.toInt() }
    val my = minOf(y, b - 1, r - 2)
    println(3 * my + 3)
}