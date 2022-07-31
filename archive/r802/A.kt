fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        var s = 0L
        fun a(i: Int, j: Int) = i * m + j + 1
        for (j in 0 until m) s += a(0, j)
        for (i in 1 until n) s += a(i, m - 1)
        println(s)
    }
}