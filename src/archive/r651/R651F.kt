package archive.r651

fun main() {
    repeat(readLine()!!.toInt()) {
        solveE()
    }
}

private fun solveE() {
    val n = readLine()!!.toInt()
    val g = List(n + 1) { ArrayList<Int>(2) }
    repeat(n - 1) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() }
        g[u].add(v)
        g[v].add(u)
    }
    val vd = IntArray(n + 1)
    fun dfs(u: Int, d: Int) {
        vd[u] = d
        for (v in g[u]) {
            if (vd[v] == 0) dfs(v, d + 1)
        }
    }
    dfs(1, 1)
    val md = vd.max()!!
    data class Ans(val d0: Int, val x: Int, val sd: Int)
    fun qd(d0: Int, u: Int = -1): Ans {
        val c = (1..n).filter { vd[it] == d0 && it != u }
        println("? ${c.size} ${c.joinToString(" ")}")
        val (x, sd) = readLine()!!.split(" ").map { it.toInt() }
        return Ans(d0, x, sd)
    }
    val p1 = qd(1)
    val p2 = qd(2)
    val q1 = qd(md)
    val q2 = qd(md - 1)
    val d2 = when {
        q1.sd == q2.sd -> qd(md)
        p1.sd == p2.sd -> qd(md - (q1.sd - p1.sd) / 2)
        else -> {
            // p1.sd - 2 * (d - 1) == q1.sd - 2 * (md - d)
            // p1.sd - q1.sd + 2 * (1 + md) == 4 * d
            val d = (p1.sd - q1.sd + 2 * (1 + md)) / 4
            val m = qd(d)
            qd(md - (q1.sd - m.sd) / 2)
        }
    }
    val dl = 1 + (p1.sd - d2.sd) / 2
    val d1_ = dl + d2.sd - (d2.d0 - dl)
    check(d1_ <= d2.d0)
    var dcur = d2.d0
    var u = d2.x
    while (dcur != d1_) {
        dcur--
        for (v in g[u]) if (vd[v] == dcur ) {
            u = v
            break
        }
    }
    val d1 = qd(d1_, u)
    println("! ${d1.x} ${d2.x}")
    check(readLine() == "Correct")
}