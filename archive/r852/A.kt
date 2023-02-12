fun main() {
    repeat(readln().toInt()) {
        val (a, b) = readln().split(" ").map { it.toLong() }
        val (n, m) = readln().split(" ").map { it.toLong() }
        println(minOf(a * n, b * n,
            n / (m + 1) * m * a + (n - n / (m + 1) * (m + 1)) * minOf(a, b)))
    }
}