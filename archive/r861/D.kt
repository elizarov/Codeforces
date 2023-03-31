fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }.withIndex()
    var sum = 0L
    for (r in 0..1) {
        val l = a.filter { it.index % 2 == r }.groupBy { it.value }
        for (b in l.values) {
            var i0 = 0
            var i1 = b.lastIndex
            var i2 = b.lastIndex
            for (j in b.indices) {
                while (i0 < j && b[j].index - b[i0].index >= k) i0++
                while (i1 > 0 && b[i1 - 1].index >= k - 1 - b[j].index) i1--
                while (i2 >= 0 && b[i2].index > 2 * n - k - 1 - b[j].index) i2--
                sum += ((i2 + 1).coerceAtMost(j) - maxOf(i0, i1)).coerceAtLeast(0)
            }
        }
    }
    println((n - k + 1).toLong() * (k - 1) / 2 - sum)
}