fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        var km = 1
        while (km * (km + 1) / 2 <= n) km++
        val d = Array(km) { LongArray(n) }
        var kl = 1
        var kr = km
        while (kl + 1 < kr) {
            val k = (kl + kr) / 2
            var dd = d[k]
            var sum = 0L
            for (i in 0 until k) {
                sum += a[i]
                dd[i] = Long.MAX_VALUE
            }
            var best = sum
            for (i in k..(n - k * (k - 1) / 2)) {
                dd[i] = best
                sum += a[i] - a[i - k]
                best = minOf(best, sum)
            }
            for (t in k - 1 downTo 1) {
                val dp = d[t + 1]!!
                dd = d[t]
                sum = 0L
                for (i in 0 until t) {
                    sum += a[i]
                    dd[i] = Long.MAX_VALUE
                }
                best = Long.MAX_VALUE
                for (i in t..(n - t * (t - 1) / 2)) {
                    if (sum < best && dp[i - t] < sum) best = sum
                    if (i == n) break
                    dd[i] = best
                    sum += a[i] - a[i - t]
                }
            }
            if (best == Long.MAX_VALUE) {
                kr = k
            } else {
                kl = k
            }
        }
        println(kl)
    }
}