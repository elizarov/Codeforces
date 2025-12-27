fun main() {
    repeat(readln().toInt()) {
        val (n, q) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        val m = 32
        val best = calcBestE(n, a, m)
        repeat(q) {
            val b = readln().toInt()
            println(solveE(b, best, m))
        }
    }
}

fun solveE(b: Int, best: IntArray, m: Int): Int {
    var max = 0
    for (j in 0..<m) {
        if (best[j] <= b) max = j
    }
    return max
}

fun calcBestE(n: Int, a: IntArray, m: Int): IntArray {
    val c = IntArray(m)
    for (i in 0..<n) {
        for (j in 0..<m) {
            if (((a[i] shr j) and 1) != 0) c[j]++
        }
    }
    val best = IntArray(m) { Int.MAX_VALUE }
    for (i in 0..<n) {
        val p = a[i]
        var q = 0
        for (j in 0..<m) {
            if (c[j] > 1 || c[j] == 1 && ((a[i] shr j) and 1) == 0) {
                q = q or (1 shl j)
            }
        }
        for (j in 0..<m) {
            val target = (1 shl j) - 1
            val missing = target and q.inv()
            val cntLo = 32 - missing.countLeadingZeroBits()
            val carryBit = 1 shl cntLo
            val maskLo = carryBit - 1
            val hi = p and maskLo.inv()
            val delta = if (missing + hi >= p) {
                missing + hi - p
            } else {
                missing + hi + carryBit - p
            }
            val result = (p + delta) or q
            val jj = result.countOneBits()
            if (delta < best[jj]) best[jj] = delta
        }
    }
    return best
}