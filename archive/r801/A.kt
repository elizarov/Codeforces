fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = List(n) { readln().split(" ").map { it.toInt() } }
        var max = Int.MIN_VALUE
        var i0 = 0
        var j0 = 0
        for (i in 0 until n) for (j in 0 until m) {
            if (a[i][j] > max) {
                max = a[i][j]
                i0 = i
                j0 = j
            }

        }
        val ans = maxOf(i0 + 1, n - i0) * maxOf(j0 + 1, m - j0)
        println(ans)
    }
}