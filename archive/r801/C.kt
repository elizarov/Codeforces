fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = List(n) { readln().split(" ").map { it.toInt() } }
        val ans = solveC(n, m, a)
        println(if(ans) "YES" else "NO")
    }
}

fun solveC(
    n: Int,
    m: Int,
    a: List<List<Int>>
): Boolean {
    val len = n + m - 1
    if (len % 2 != 0) return false
    val la = Array(n) { IntArray(m) }
    val ra = Array(n) { IntArray(m) }
    for (i in 0 until n) for (j in 0 until m) {
        val z = if (a[i][j] > 0) 1 else 0
        var l = z
        var r = z
        if (i > 0) {
            l += la[i - 1][j]
            r += ra[i - 1][j]
        }
        if (j > 0) {
            if (i > 0) {
                l = minOf(l, z + la[i][j - 1])
                r = maxOf(r, z + ra[i][j - 1])
            } else {
                l = z + la[i][j - 1]
                r = z + ra[i][j - 1]
            }
        }
        la[i][j] = l
        ra[i][j] = r
    }
    return (len / 2) in la[n - 1][m - 1]..ra[n - 1][m - 1]
}