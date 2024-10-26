fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var l = 0
        var r = n - 1
        for (i in 0 until n) {
            l = maxOf(l, i - a[i] + 1)
            r = minOf(r, i + a[i] - 1)
        }
        if (l > r) {
            println(0)
            return@repeat
        }
        val ans = r - l + 1
        val dl = IntArray(l + 2) { Int.MAX_VALUE }
        val dr = IntArray(n - r + 1) { Int.MAX_VALUE }
        for (i in 0 until l) dl[l - i] = a[i] - (r - i) - 1
        for (i in r + 1 until n) dr[i - r] = a[i] - (i - l) - 1
        for (i in l - 1 downTo 0) dl[i] = minOf(dl[i], dl[i + 1])
        for (i in n - r - 2 downTo 0) dr[i] = minOf(dr[i], dr[i + 1])
        var i = 0
        var j = 0
        while (i < l || j < n - r - 1) {
            if (i < l && i < dr[j + 1]) i++
            else if (j < n - r - 1 && j < dl[i + 1]) j++
            else {
                println(0)
                return@repeat
            }
        }
        println(ans)
    }
}