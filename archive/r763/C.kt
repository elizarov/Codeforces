fun main() {
    val ans = List(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val h = readLine()!!.split(" ").map { it.toLong() }
        var l = 0L
        var r = h.sum() / n + 2
        val hd = LongArray(n)
        while (l < r - 1) {
            val h0 = (l + r) / 2
            for (i in 0 until n) hd[i] = 0
            for (i in n - 1 downTo 2) {
                if (h[i] + hd[i] > h0) {
                    val d = minOf((h[i] + hd[i] - h0) / 3, h[i] / 3)
                    hd[i - 2] += 2 * d
                    hd[i - 1] += d
                }
            }
            val ok = (0 until n).all { i -> h[i] + hd[i] >= h0 }
            if (ok) l = h0 else r = h0
        }
        l
    }
    println(ans.joinToString("\n"))
}