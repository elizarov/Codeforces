fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }

        fun check(m: Long): Long {
            val ops0 = a[0] + m
            var max = ops0
            var min = 0L
            var ok = true
            for (i in 1 until n) {
                var c = a[i] + m
                if (c < min) {
                    ok = false
                    break
                }
                c -= min
                if (c <= max) {
                    max = c
                } else {
                    c -= max
                    min += c
                }
            }
            if (ok) return ops0 + min
            return -1L
        }

        var l = (-(a.minOrNull()!!)).coerceAtLeast(0).toLong() - 1
        var r = 1e9.toLong()
        var rops = -1L
        while (rops < 0) {
            r *= 2
            rops = check(r)
        }
        while (r - l > 1) {
            val m = (r + l) / 2
            val ops = check(m)
            if (ops >= 0) {
                r = m
                rops = ops
            } else {
                l = m
            }
        }
        println(r + rops)
    }
}