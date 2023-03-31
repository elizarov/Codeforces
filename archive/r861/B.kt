fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val c = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        var ans = 0L
        val a = IntArray(n)
        for (j in 0 until m) {
            for (i in 0 until n) a[i] = c[i][j]
            a.sort()
            var s = a.sumOf { it.toLong() }
            for (i in 0 until n) {
                s -= a[i]
                ans += s - (n - i - 1) * a[i].toLong()
            }
        }
        println(ans)
    }
}