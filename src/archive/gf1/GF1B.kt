package archive.gf1

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }
        println(solveB(k, a))
    }
}

private fun solveB(k: Int, a: List<Int>): Int {
    val inc = a.zipWithNext { a0, a1 -> if (a0 == a1) 0 else 1 }.sum()
    if (k == 1) {
        return if (inc == 0) 1 else -1
    }
    return ((inc + k - 2) / (k - 1)).coerceAtLeast(1)
}