package archive.r662

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) { readLine()!!.toCharArray() }
    val d = Array(n) { IntArray(m) }
    var ans = 0L
    for (i in 0 until n) for (j in 0 until m) {
        d[i][j] = 1
        val a0 = a[i][j]
        if (i > 1 && j in 1..m - 2 && a[i - 1][j - 1] == a0 && a[i - 1][j] == a0 && a[i - 1][j + 1] == a0) {
            val dp = minOf(d[i - 1][j - 1], d[i - 1][j], d[i - 1][j + 1])
            d[i][j] = dp
            val i1 = i - 2 * dp
            if (i1 >= 0 && a[i1][j] == a0) {
                d[i][j] = dp + 1
            }
        }
        ans += d[i][j]
    }
    println(ans)
}