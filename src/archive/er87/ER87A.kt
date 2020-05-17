package archive.er87

fun main() {
    repeat(readLine()!!.toInt()) {
        val (a, b, c, d) = readLine()!!.split(" ").map { it.toLong() }
        println(solveA(a, b, c, d))
    }
}

fun solveA(a: Long, b: Long, c: Long, d: Long): Long {
    if (b >= a) return b
    if (d >= c) return -1
    val n = (a - b + c - d - 1) / (c - d)
    return b + n * c
}
