fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val s = a.sumOf { it.toLong() }
        println(s - 2 * a[n - 2])
    }