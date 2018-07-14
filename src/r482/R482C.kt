package r482c

fun main(args: Array<String>) {
    val (n, x, y) = readLine()!!.split(" ").map { it.toInt() }
    val adj = List(n + 1) { ArrayList<Int>() }
    repeat(n - 1) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        adj[a] += b
        adj[b] += a
    }
    val tot = n.toLong() * (n - 1)
    val v = BooleanArray(n + 1)
    fun go(i : Int): Int {
        if (i == y) return -1
        v[i] = true
        var s = 1
        for (j in adj[i]) {
            if (v[j]) continue
            val t = go(j)
            if (t == -1) s = -1
            else if (s > 0) s += t
        }
        return s
    }
    v[x] = true
    val cx = 1 + adj[x].map { go(it) }.filter { it >= 0 }.sum()
    var cy = 0
    for (i in 1..n) if (!v[i]) cy++
    val res = n.toLong() * (n - 1) - cx.toLong() * cy
    println(res)
}