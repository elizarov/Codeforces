fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        var found = false
        for (z in 1..n step 2) {
            val l = k - z / 2 - 1
            val r = n - k - z / 2
            if (l < 0 || r < 0) continue
            if (l == 0 && r == 0) {
                found = true
                println(1)
                println(1)
                break
            }
            if (r == 0 || l == 0) continue
            if (r.countTrailingZeroBits() == l.countTrailingZeroBits()) {
                val d = 1 shl r.countTrailingZeroBits()
                val ll = l / d
                val rr = r / d
                val a = IntArray(2 * d + 1)
                for (i in 0..<d) a[i] = 1 + ll * i
                a[d] = l + 1
                for (i in d + 1..<a.size) a[i] = n - r + 1 + rr * (i - d - 1)
                found = true
                println(a.size)
                println(a.joinToString(" "))
                break
            }
        }
        if (!found) {
            println(-1)
        }

    }
}