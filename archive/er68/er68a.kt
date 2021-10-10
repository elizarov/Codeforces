package archive.er68

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        solveCase()
    }
}

private fun solveCase() {
    val (n, x) = readLine()!!.split(" ").map { it.toInt() }
    println(2 * x)
}

