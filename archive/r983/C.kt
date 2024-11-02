fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.sorted()
        var best = n - 2
        for (i in 0..n - 3) {
            val s = a[i] + a[i + 1]
            var l = i + 1
            var r = n
            while (l < r - 1) {
                val m = (l + r) / 2
                if (a[m] >= s) {
                    r = m
                } else {
                    l = m
                }
            }
            best = minOf(best, i + n - r)
        }
        println(best)
    }
}