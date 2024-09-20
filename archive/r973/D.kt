fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toLong() }.toLongArray()
        var l = a.min() - 1
        var r = a.max()
        while (l < r - 1) {
            val m = (l + r) / 2
            var x = 0L
            for (i in 0..<n) {
                x += a[i]
                if (x > m) {
                    x -= m
                } else {
                    x = 0
                }
            }
            if (x == 0L) {
                r = m
            } else {
                l = m
            }
        }
        val max = r
        var rem = 0L
        for (i in 0..<n) {
            a[i] += rem
            if (a[i] > max) {
                rem = a[i] - max
                a[i] = max
            } else {
                rem = 0
            }
        }
        check(rem == 0L)
        l = a.min()
        r = max + 1
        while (l < r - 1) {
            val m = (l + r) / 2
            var x = 0L
            for (i in n - 1 downTo 0) {
                x = a[i] - x
                if (x < m) {
                    x = m - x
                } else {
                    x = 0
                }
            }
            if (x == 0L) {
                l = m
            } else {
                r = m
            }
        }
        val min = l
        println(max - min)
    }
}