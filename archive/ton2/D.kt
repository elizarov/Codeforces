fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val c = List(n) { readln().split(" ").map { it.toInt() } }
        val w = c.map { a -> a.withIndex().sumOf { (i, v) -> i.toLong() * v } }
        val ws = w.withIndex().sortedByDescending { it.value }
        val idx = ws[0].index + 1
        val w0 = ws[0].value
        val w1 = ws[1].value
        val cnt = w0 - w1
        println("$idx $cnt")
    }
}