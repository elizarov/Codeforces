fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }
        val b = readln().split(" ").map { it.toInt() }
        val sum = LongArray(n + 1)
        for (i in 0..<n) sum[i + 1] = sum[i] + a[i]
        val d = Array(n + 1) { LongArray(m) }
        for (i in n - 1 downTo 0) {
            var l = i
            for (k in m - 1 downTo 0) {
                val lim = sum[i] + b[k]
                var r = n + 1
                while (l < r - 1) {
                    val mid = (l + r) / 2
                    if (sum[mid] <= lim) {
                        l = mid
                    } else {
                        r = mid
                    }
                }
                if (l == i || d[l][k] == -1L) {
                    d[i][k] = -1
                } else {
                    d[i][k] = d[l][k] + m - k - 1
                    if (k < m - 1 && d[i][k + 1] >= 0) d[i][k] = minOf(d[i][k], d[i][k + 1])
                }
            }
        }
        println(d[0][0])
    }
}