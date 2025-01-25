fun main() {
    for (n in 4..15) {
        println("Testing n=$n")
        for (w in 1..n - 2) {
            val h = IntArray(w + 1)
            fun scan(i: Int, rem: Int): Boolean {
                if (i > w) {
                    check(rem == 0)
                    return testD(n, w, h)
                }
                val min = when (i) {
                    w -> rem
                    1 -> 1
                    else -> 0
                }
                for (j in min..rem) {
                    h[i] = j + 1
                    if (!scan(i + 1, rem - j)) return false
                }
                return true
            }
            if (!scan(1, n - w - 1)) return
        }
    }
}

fun testD(n: Int, w: Int, h: IntArray): Boolean {
    val p = IntArray(n)
    val c = IntArray(n)
    val last = IntArray(w + 1)
    var cur = 1
    for (d in 1..h.max()) for (i in 1..w) if (d <= h[i]) {
        c[cur] = i
        p[cur] = last[i]
        last[i] = cur
        cur++
    }
    check(cur == n)
    val expected = p.drop(1)
    val questions = ArrayList<Pair<Int,Int>>()
    val result = solveD(n) { a, b ->
        questions += a to b
        c[a] != c[b]
    }
    val limit = 2 * n - 6
    if (result != expected || questions.size > limit) {
        println("=== ERROR ===")
        println("n=$n, w=$w, h=${h.drop(1).joinToString(",")}")
        println("expected  ${expected.joinToString(" ")}")
        println(" result   ${result.joinToString(" ")}")
        println("questions ${questions.size} (limit $limit) $questions")
        return false
    }
    return true
}