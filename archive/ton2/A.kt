fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = readln()
        val b = readln()
        println(if (solveA(n, m, a, b)) "YES" else "NO")
    }
}

fun solveA(n: Int, m: Int, a: String, b: String): Boolean {
    if (a == b) return true
    if (n <= m) return false
    val r = b.substring(1)
    if (a.substring(n - (m - 1)) != r) return false
    val s = a.substring(0, n - (m - 1))
    val b0 = b[0]
    return s.contains(b0)
}
