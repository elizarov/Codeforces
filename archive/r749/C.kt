fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) { readLine()!!.toCharArray() }
    val g = Array(n) { IntArray(m) { -1 } }
    for (i in 0 until n) for (j in 0 until m) {
        if (a[i][j] == 'X') continue
        if (i == 0 || j == 0) {
            g[i][j] = j
            continue
        }
        g[i][j] = maxOf(g[i - 1][j], g[i][j - 1])
    }

}