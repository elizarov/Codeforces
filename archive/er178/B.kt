fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val best = a.runningReduce { x, y -> maxOf(x, y) }
        val ans = LongArray(n) { 0 }
        var sum = 0L
        for (k in 1..n) {
            val last = a[n - k]
            sum += last
            ans[k - 1] = sum
            if (k == n) break
            val b = best[n - k - 1]
            if (b <= last) continue
            ans[k - 1] = sum - last + b
        }
        println(ans.joinToString(" "))
    }
}