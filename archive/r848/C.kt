fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val a = readln()
        val b = readln()
        val c = a.toList().distinct().withIndex().associate { it.value to it.index }
        fun pc(m: Int): Long = m.toLong() * (m + 1) / 2
        val cc = c.size
        if (cc <= k) {
            println(pc(n))
        } else {
            var best = 0L
            for (mask in 0 until (1 shl cc)) if (mask.countOneBits() == k) {
                var last = -1
                var sum = 0L
                for (i in 0 until n) {
                    val match = a[i] == b[i] || (1 shl c[a[i]]!!) and mask != 0
                    if (match && last == -1) {
                        last = i
                    } else if (!match && last >= 0) {
                        sum += pc(i - last)
                        last = -1
                    }
                }
                if (last >= 0) sum += pc(n - last)
                best = maxOf(best, sum)
            }
            println(best)
        }
    }
}