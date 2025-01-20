fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var res = n.toLong() * (n + 1) / 2
        val c = IntArray(n + 1)
        val d = IntArray(n + 1)
        for (m in 1..9) {
            var sc = 0
            var sd = 0
            for (i in 0..<n) {
                if (a[i] <= m) sd++ else sd--
                if (a[i] == m) sc++
                c[i + 1] = sc
                d[i + 1] = sd
            }
            val gd = d.withIndex().groupBy { it.value }.values
            for (gi in gd) {
                val cnt = gi.size
                res -= cnt.toLong() * (cnt - 1) / 2
                val gc = gi.groupingBy { c[it.index] }.eachCount().values
                for (cc in gc) {
                    res += cc.toLong() * (cc - 1) / 2
                }
            }
        }
        println(res)
    }
}