fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        val same = IntArray(n)
        for (j in 0 until n) {
            for (i in 0 until j) {
                if (a[i][j] != a[j][i]) break
                same[j]++
            }
        }
        for (i in 0 until n) for (j in i + 1 until n) {
            if (a[j][i] >= a[i][j]) continue
            if (same[j] != i) continue
            for (k in i until n) if (k != j) {
                val t = a[k][j]
                a[k][j] = a[j][k]
                a[j][k] = t
            }
        }
        repeat(n) { println(a[it].joinToString(" ")) }
    }
}