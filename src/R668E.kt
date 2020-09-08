import algo.*

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val d = Array(n) { readLine()!!.toCharArray() }
    val hc = (n - 1) * m
    val vc = (m - 1) * n
    val g = Graph(hc + vc)
    fun v(i: Int, j: Int) = hc + j * n + i
    for (i in 0 until n - 1) for (j in 0 until m) {
        val h = i * m + j
        if (d[i][j] == '#') {
            if (j > 0 && d[i][j - 1] == '#') g.add(h, v(i, j - 1))
            if (j < m - 1 && d[i][j + 1] == '#') g.add(h, v(i, j))
        }
        if (d[i + 1][j] == '#') {
            if (j > 0 && d[i + 1][j - 1] == '#') g.add(h, v(i + 1, j - 1))
            if (j < m - 1 && d[i + 1][j + 1] == '#') g.add(h, v(i + 1, j))
        }
    }
    val removed = g.maxBiMatching(hc).count { it >= 0 }
    val total = d.sumOf { it.count { it == '#' }}
    println(total - removed)
}