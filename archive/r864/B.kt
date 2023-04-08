fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val a = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        val f = Array(n) { BooleanArray(n) }
        var cnt = 0
        var center = false
        for (i in 0 until n) for (j in 0 until n) {
            if (f[i][j]) continue
            val i1 = n - 1 - i
            val j1 = n - 1 - j
            f[i1][j1] = true
            if (f[i][j]) {
                center = true
                continue
            }
            f[i][j] = true
            if (a[i][j] != a[i1][j1]) cnt++
        }
        val rem = n * n - cnt
        val can = when {
            cnt > k -> false
            else -> center || (k - cnt) % 2 == 0
        }
        println(if (can) "YES" else "NO")
    }
}