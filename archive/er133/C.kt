fun main() {
    repeat(readln().toInt()) {
        val m = readln().toInt()
        val a = List(2) { readln().split(" ").map { it.toInt() } }
        val b = List(2) { IntArray(m + 1) }
        for (i in 0..1) {
            for (j in m - 1 downTo 0) {
                b[i][j] = maxOf(b[i][j + 1], a[i][j] - (m - 1 - j))
            }
        }
        val f = List(2) { IntArray(m + 1) }
        for (i in 0..1) {
            for (j in m - 1 downTo 0) {
                f[i][j] = maxOf(f[i][j + 1] - 1, a[i][j])
            }
        }
        var i = 0
        var j = 0
        var cur = 0
        var best = Int.MAX_VALUE
        for (t in 1 until 2 * m) {
            var i1 = i
            var j1 = j
            when (t % 2) {
                0 -> j1++
                1 -> i1 = 1 - i
            }
            val end = maxOf(cur, f[i][j + 1]) + (m - 1 - j)
            val back = maxOf(end, b[1 - i][j1]) + (m - j1)
            best = minOf(best, back)
            i = i1
            j = j1
            cur = maxOf(cur, a[i][j]) + 1
        }
        println(best)
    }
}