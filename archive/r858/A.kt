fun main() {
    repeat(readln().toInt()) {
        val (x1, y1, x2, y2) = readln().split(" ").map { it.toInt() }
        val res = when {
            y2 < y1 -> -1
            x1 + (y2 - y1) < x2 -> -1
            else -> x1 - x2 + 2 * (y2 - y1)
        }
        println(res)
    }
}