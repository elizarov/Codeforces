fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = IntArray(n)
        val b = IntArray(n)
        repeat(n) { i ->
            val (ai, bi) = readLine()!!.split(" ").map { it.toInt() }
            a[i] = ai
            b[i] = bi
        }
        val b1c = FenwickTree(n + 1)
        val b2cd = FenwickTree(n + 1)
        val b2cu = FenwickTree(n + 1)
        val aOrdIdx = (0 until n).sortedBy { a[it] }
        var abds = 0L
        var prevAv = -1
        var prevAk = 0
        for ((k, i) in aOrdIdx.withIndex()) {
            if (a[i] != prevAv) {
                for (k1 in prevAk until k) {
                    val i1 = aOrdIdx[k1]
                    b2cd.update(b[i1], b1c.sum(b[i1] - 1))
                    // TODO:???
                }
                for (k1 in prevAk until k) {
                    val i1 = aOrdIdx[k1]
                    b1c.update(b[i1], 1)
                }
                prevAv = a[i]
                prevAk = k
            }
            abds += b2cd.sum(b[i] - 1)
            abds += b2cu.sum(b[i] + 1, n)
            abds += b1c.sum(b[i] - 1) * b1c.sum(b[i] + 1, n)
        }
        val ads = diffCount(a)
        val bds = diffCount(b)
        println(ads + bds - abds)
    }
}

private fun diffCount(a: IntArray): Long {
    val ac = a.toList().groupingBy { it }.eachCount().values
    var s1 = 0L
    var s2 = 0L
    var s3 = 0L
    for (c in ac) {
        s3 += s2 * c
        s2 += s1 * c
        s1 += c
    }
    return s3
}

class FenwickTree(n: Int) {
    val a = LongArray(n)

    fun sum(toIndex: Int): Long { // inclusive
        var i = toIndex
        var sum = 0L
        while (i >= 0) {
            sum += a[i]
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun sum(fromIndex: Int, toIndex: Int): Long = // inclusive
        if (toIndex < fromIndex) 0 else
            sum(toIndex) - sum(fromIndex - 1)

    fun update(index: Int, delta: Long) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}