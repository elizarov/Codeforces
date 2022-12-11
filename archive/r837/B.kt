fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val p = IntArray(n) { -1 }
        repeat(m) {
            val (x, y) = readln().split(" ").map { it.toInt() - 1 }.sorted()
            if (x > p[y]) p[y] = x
        }
        var i = 0
        var j = 1
        var ans = 0L
        while (i < n) {
            var lb = -1
            while (j < n) {
                lb = p[j]
                if (lb >= i) break
                j++
            }
            val k = (j - i).toLong()
            ans += k * (k + 1) / 2
            if (lb < i) break
            val t = (j - lb - 1).toLong()
            ans -= t * (t + 1) / 2
            i = lb + 1
            j++
        }
        println(ans)
    }
}