package archive.er105

fun main() {
    val n = readLine()!!.toInt()
    val a = Array(n) {
        readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }
    val p = ArrayList<Int>(n)
    val c = ArrayList<Int>(n)
    repeat(n) { i ->
        p.add(-1)
        c.add(a[i][i])
    }
    fun recover(s: List<Int>): Int {
        if (s.size == 1) return s[0]
        var max = 0
        for (i in 0 until s.size - 1) {
            val u = s[i]
            for (j in i + 1 until s.size) {
                val v = s[j]
                max = maxOf(max, a[u][v])
            }
        }
        val r = p.size
        p.add(-1)
        c.add(max)
        val f = BooleanArray(s.size)
        for (i in 0 until s.size) {
            if (f[i]) continue
            f[i] = true
            val g = ArrayList<Int>()
            val u = s[i]
            g.add(u)
            for (j in i + 1 until s.size) {
                val v = s[j]
                if (a[u][v] < max) {
                    f[j] = true
                    g.add(v)
                }
            }
            val rr = recover(g)
            p[rr] = r
        }
        return r
    }
    val r = recover((0 until n).toList())
    println(p.size)
    println(c.joinToString(" "))
    println(r + 1)
    for ((u, v) in p.withIndex()) {
        if (u != r) println("${u + 1} ${v + 1}")
    }
}