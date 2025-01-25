fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val r = Array(n) { readln().split(" ").map { it.toInt() } }
        val x = r.map { it[0] }
        val y = r.map { it[1] }
        val sx = x.sum() - x[0] + m
        val sy = y.sum() - y[0] + m
        println(2 * sx + 2 * sy)
    }
}