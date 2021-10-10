package archive.gr9

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val a = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
        if (solveB(n, m, a)) {
            println("YES")
            a.forEach { println(it.joinToString(" ")) }
        } else {
            println("NO")
        }
    }
}

fun solveB(n: Int, m: Int, a: Array<IntArray>): Boolean {
    data class P(val i: Int, val j: Int)
    val r = Array(n) { IntArray(m) }
    val k = Array(n) { IntArray(m) }
    val z = HashMap<P, Int>()
    val z0 = ArrayList<P>()
    for (i in 0 until n) for (j in 0 until m) {
        z0.clear()
        if (i > 0) {
            k[i][j]++
            if (a[i - 1][j] > 0) r[i][j]++ else z0.add(P(i - 1, j))
        }
        if (j > 0) {
            k[i][j]++
            if (a[i][j - 1] > 0) r[i][j]++ else z0.add(P(i, j - 1))
        }
        if (i < n - 1) {
            k[i][j]++
            if (a[i + 1][j] > 0) r[i][j]++ else z0.add(P(i + 1, j))
        }
        if (j < m - 1) {
            k[i][j]++
            if (a[i][j + 1] > 0) r[i][j]++ else z0.add(P(i, j + 1))
        }
        if (a[i][j] > k[i][j]) return false
        if (a[i][j] == 0) continue
        if (a[i][j] < r[i][j]++) a[i][j] = r[i][j]++
        if (a[i][j] > r[i][j]) {
            for (p in z0) {
                                
            }
        }
    }


    return true
}
