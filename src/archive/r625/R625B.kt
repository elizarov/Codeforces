package archive.r625

class G(n: Int, m: Int) {
    val gf = IntArray(n) { -1 }
    val gv = IntArray(m)
    val gn = IntArray(m)
    fun add(j: Int, u: Int, v: Int) {
        gv[j] = v
        gn[j] = gf[u]
        gf[u] = j
    }
    inline fun eachV(u: Int, f: (Int) -> Unit) {
        var t = gf[u]
        while (t >= 0) {
            f(gv[t])
            t = gn[t]
        }
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val g = G(n, m)
    val h = G(n, m)
    for (j in 0 until m) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g.add(j, u, v)
        h.add(j, v, u)
    }
    val k = readLine()!!.toInt()
    val p = readLine()!!.split(" ").map { it.toInt() - 1 }
    val dd = IntArray(n)
    val qv = IntArray(n)
    val qf = BooleanArray(n)
    var qh = 0
    var qt = 0
    fun enqueue(v: Int, d: Int) {
        if (qf[v]) return
        qf[v] = true
        qv[qt++] = v
        dd[v] = d
    }
    enqueue(p.last(), 0)
    while (qh < qt) {
        val v = qv[qh++]
        h.eachV(v) { u -> enqueue(u, dd[v] + 1) }
    }
    var min = 0
    var max = 0
    for (i in k - 3 downTo 0) {
        val u = p[i]
        val v = p[i + 1]
        val du = dd[u]
        val dv = dd[v]
        var f = du != dv + 1
        if (f) min++
        g.eachV(u) { v1 -> if (v1 != v && du == dd[v1] + 1) f = true }
        if (f) max++
    }
    println("$min $max")
}