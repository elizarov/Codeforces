package archive.er89

fun main() {
    repeat(readLine()!!.toInt()) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        println(solveA(a, b))
    }
}

fun solveA(a0: Int, b0: Int): Int {
    val (a, b) = listOf(a0, b0).sorted()
    if (2 * a <= b) {
        return a
    }
    val x = (2 * a - b) / 3
    val y = minOf(a - 2 * x, (b - x) / 2)
    return x + y
}