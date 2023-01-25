fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
        val b = IntArray(n)
        val next = IntArray(n)
        val prev = IntArray(n)
        for (i in 0 until n) {
            b[a[i]] = i
            next[i] = i + 1
            prev[i] = i - 1

        }
        var bc = 0
        for (i in 1 until n) {
            if (a[i - 1] > a[i]) bc++
        }
        fun remove(x: Int) {
            val j = b[x]
            val i = prev[j]
            val k = next[j]
            if (i >= 0 && a[i] > a[j]) bc--
            if (k < n && a[j] > a[k]) bc--
            if (i >= 0) next[i] = k
            if (k < n) prev[k] = i
            if (i >= 0 && k < n && a[i] > a[k]) bc++
        }
        var i = 0
        var j = n - 1
        var ans = 0
        while (bc > 0 && i < j) {
            remove(i++)
            remove(j--)
            ans++
        }
        println(ans)
    }
}