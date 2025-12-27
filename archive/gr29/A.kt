fun main() {
    repeat(readln().toInt()) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        val ans = when {
            y > x -> 2
            y < x - 1 && y >= 2 -> 3
            else -> -1
        }
        println(ans)
    }
}