fun main() {
    repeat(readLine()!!.toInt()) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        println(solveB(x, y))
    }
}

fun solveB(x: Int, y: Int): Long {
    if (y < x) {
        return x.toLong() + y
    } else {
        val a = (y / x - 1).toLong().coerceAtLeast(1)
        return (a * x + y) / 2
    }
}
