package archive.r526

import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val w = readLine()!!.split(" ").map { it.toLong() }.toLongArray()
    val g = Array(n) { ArrayList<E>() }
    repeat(n - 1) {
        val (ui, vi, ci) = readLine()!!.split(" ").map { it.toInt() }
        addEdge(g, ui - 1, vi - 1, ci)
    }
    println(solveBestPath(n, w, g))
}

fun solveBestPath(
    n: Int,
    w: LongArray,
    g: Array<ArrayList<E>>
): Long {
    var best = 0L
    fun solve(u: Int, p: Int): Long {
        var b0 = 0L
        var b1 = 0L
        for (e in g[u]) {
            if (e.v == p) continue
            val b = max(0, solve(e.v, u) - e.c)
            if (b > b0) {
                b1 = b0
                b0 = b
            } else if (b > b1) {
                b1 = b
            }
        }
        val ab0 = w[u] + b0
        val ab = ab0 + b1
        best = max(best, ab)
        return ab0
    }
    solve(0, -1)
    return best
}

fun addEdge(g: Array<ArrayList<E>>, ui: Int, vi: Int, ci: Int) {
    g[ui].add(E(vi, ci))
    g[vi].add(E(ui, ci))
}

class E(val v: Int, val c: Int)
