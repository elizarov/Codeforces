fun main() {
    repeat(readln().toInt()) {
        val (x, y, z, k) = readln().split(" ").map { it.toLong() }
        fun tDeal(t: Long): Long = k * t * (t + 1) / 2
        fun bestAt(t: Long): Long {
            val rem = z - tDeal(t)
            val minD = t * k + 1
            val maxD = (t + 1) * k
            var l = ((rem + maxD - 1) / maxD)
            var r = ((rem + minD - 1) / minD)
            while (l < r - 3) {
                val m1 = (2 * l + r) / 3
                val m2 = (l + 2 * r) / 3
                val d1 = (rem + m1 - 1) / m1
                val d2 = (rem + m2 - 1) / m2
                val c1 = y * m1 + x * (d1 - minD).coerceAtLeast(0)
                val c2 = y * m2 + x * (d2 - minD).coerceAtLeast(0)
                if (c1 < c2) {
                    r = m2
                } else {
                    l = m1
                }
            }
            var res = Long.MAX_VALUE
            for (w in l..r) {
                val d = (rem + w - 1) / w
                val c = y * (w + t)  + x * maxOf(d, minD)
                res = minOf(res, c)
            }
            return res
        }

        var r = 1L
        while (tDeal(r) < z) r *= 2
        var l = 0L
        while (l < r - 1) {
            val m = (l + r) / 2
            if (tDeal(m) < z) {
                l = m
            } else {
                r = m
            }
        }
        r = l
        l = 0
        for (t in l..r) {
            println("t=$t, best=${bestAt(t)}")
        }
        while (l < r - 3) {
            val m1 = (2 * l + r) / 3
            val m2 = (l + 2 * r) / 3
            if (bestAt(m1) < bestAt(m2)) {
                r = m2
            } else {
                l = m1
            }
        }
        var best = Long.MAX_VALUE
        for (t in l..r) {
            best = minOf(best, bestAt(t))
        }
        println(best)
    }
}