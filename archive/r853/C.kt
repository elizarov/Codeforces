fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
        val s = IntArray(n + m)
        val t = IntArray(n + m)
        for (j in 1..m) {
            val (p, v1) = readln().split(" ").map { it.toInt() - 1 }
            val v0 = a[p]
            if (v1 == v0) continue
            t[v0] += j - s[v0]
            a[p] = v1
            s[v1] = j
        }
        for (i in 0 until n) {
            val v = a[i]
            t[v] += m + 1 - s[v]
        }
        var ans = n.toLong() * m * (m + 1)
        for (tt in t) {
            ans -= tt.toLong() * (tt - 1) / 2
        }
        println(ans)
    }
}