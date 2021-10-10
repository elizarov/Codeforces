fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val w = readLine()!!.split(" ").map { it.toInt() }
        val gc = IntArray(n)
        repeat(n - 1) { j ->
            val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
            gc[u]++
            gc[v]++
        }
        val ans = LongArray(n - 1)
        var sum = w.withIndex().sumOf { we -> we.value.toLong() * gc[we.index] }
        ans[n - 2] = sum
        var lc = n - 3
        for (we in w.withIndex().sortedBy { it.value }) {
            if (lc < 0) break
            for (r in 1 until gc[we.index]) {
                sum -= we.value
                ans[lc--] = sum
                if (lc < 0) break
            }
        }
        println(ans.joinToString(" "))
    }
}