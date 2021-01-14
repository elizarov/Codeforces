fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val p = IntArray(k)
        for (i in 0 until k) p[i] = i + 1
        val f = n - k + 1
        var l = k - f
        var r = k - 1
        while (l < r) {
            val t = p[l]
            p[l] = p[r]
            p[r] = t
            l++
            r--
        }
        println(p.joinToString(" "))
    }
}