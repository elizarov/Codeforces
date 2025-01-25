fun main() {
    repeat(readln().toInt()) {
        var (n, l, r) = readln().split(" ").map { it.toInt() }
        l--; r--
        val a = readln().split(" ").map { it.toInt() }
        val r0 = a.subList(l, r + 1).sortedDescending()
        val s0 = r0.sumOf { it.toLong() }
        fun choose(l1: Int, r1: Int): Long {
            if (l1 > r1) return s0
            val r1 = a.subList(l1, r1 + 1).sorted()
            var i = 0
            var ans = s0
            while (i < r0.size && i < r1.size && r1[i] < r0[i]) {
                ans -= r0[i] - r1[i]
                i++
            }
            return ans
        }
        println(minOf(choose(0, l - 1), choose(r + 1, n - 1)))
    }
}

