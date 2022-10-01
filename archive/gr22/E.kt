fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val mod = 998244353
        fun Int.modInv(): Int {
            var t = 0
            var r = mod
            var newT = 1
            var newR = this
            while (newR != 0) {
                val q = r / newR
                t = newT.also { newT = t - q * newT }
                r = newR.also { newR = r - q * newR }
            }
            check(r == 1)
            if (t < 0) t += mod
            return t
        }
        val p2 = LongArray(n + 1)
        p2[0] = 1
        for (k in 1..n) p2[k] = (2 * p2[k - 1]) % mod
        fun choose(k: Int, n: Int): Long {
            var sum = 1L
            var cur1 = 1L
            var cur2 = 1L
            for (i in 1..k) {
                val inv = i.modInv()
                cur1 = (cur1 * (n - i + 1)) % mod
                cur1 = (cur1 * inv) % mod
                cur2 = (cur2 * (k - i + 1)) % mod
                cur2 = (cur2 * inv) % mod
                sum = (sum + cur1 * cur2) % mod
            }
            return sum
        }
        var i = 0
        var j = n - 1
        var si = 0L
        var sj = 0L
        var cnt = 1L
        while (true) {
            var ic = 0
            var jc = 0
            if (i != 0) { ic++; jc++ }
            while (i <= j && a[i] == 0) { i++; ic++ }
            while (i <= j && a[j] == 0) { j--; jc++ }
            if (i == n) ic--
            val mul = if (i > j) p2[ic] else choose(minOf(ic, jc), maxOf(ic, jc))
            cnt = cnt * mul % mod
            if (i >= j) break
            si += a[i++]
            sj += a[j--]
            while (si != sj && i <= j) {
                if (si < sj) si += a[i++] else sj += a[j--]
            }
            if (si != sj) break
        }
        println(cnt)
    }
}