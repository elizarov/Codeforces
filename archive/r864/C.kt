fun main() {
    repeat(readln().toInt()) {
        solveC()
    }
}

fun solveC() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val d1 = query(1, 1)
    if (d1 == 0) {
        ans(1, 1)
        return
    }
    if (d1 + 1 <= m) {
        val d2 = query(1, d1 + 1)
        if (d2 < d1) {
            ans(d2 + 1, d1 + 1)
            return
        }
    }
    val d3 = query(d1 + 1, 1)
    ans(d1 + 1, d3 + 1)
}

private fun ans(i: Int, j: Int) {
    println("! $i $j")

}

private fun query(i: Int, j: Int): Int {
    println("? $i $j")
    return readln().toInt()
}

