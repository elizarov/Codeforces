fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val (x1, y1, x2, y2) = readln().split(" ").map { it.toInt() }
        fun i(x: Int, y: Int): Int = if (x in 1..n && y in 1..m) 1 else 0
        fun s(x: Int, y: Int): Int = i(x + 1, y) + i(x - 1, y) + i(x, y + 1) + i(x, y - 1)
        println(minOf(s(x1, y1), s(x2, y2)))
    }
}