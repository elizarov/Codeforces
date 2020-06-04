package archive.r647

fun main() = System.`in`.bufferedReader().run {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { ArrayList<Int>(2) }
    repeat(m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[a].add(b)
        g[b].add(a)
    }
    val t = readLine()!!.split(" ").map { it.toInt() - 1 }
    val r = solve(n, t, g)
    if (r == null) {
        println(-1)
    } else {
        println(r.joinToString(" "))
    }
}

private fun solve(n: Int, t: List<Int>, g: Array<ArrayList<Int>>): List<Int>? {
    var prev = 0
    val r = ArrayList<Int>()
    val f = BooleanArray(n)
    val z = BooleanArray(n)
    for ((b, j) in t.withIndex().sortedBy { it.value }) {
        if (j == prev + 1) prev = j
        if (j != prev) return null
        for (a in g[b]) if (f[a]) z[t[a]] = true
        for (i in 0 until j) if (!z[i]) {
            return null
        }
        if (z[j]) {
            return null
        }
        z.fill(false, 0, j)
        r.add(b + 1)
        f[b] = true
    }
    return r
}