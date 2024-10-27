fun main() {
    repeat(readln().toInt()) {
        val (n, m, r, c) = readln().split(" ").map { it.toInt() }
        val sum = (m - c).toLong() + (n - r).toLong() * (m - 1 + m)
        println(sum)
    }
}