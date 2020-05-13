package archive.r641

import java.util.*
import kotlin.collections.HashMap

fun main() {
    val (n, m, t) = readLine()!!.split(" ").map { it.toInt() }
    val s = Array(n) { readLine()!!.toCharArray() }
    val a = BitSet(n * m)
    for (i in 0 until n) for (j in 0 until m) if (s[i][j] == '1') a.set(i * m + j)
    val l = ArrayList<BitSet>()
    val bi = simGames(n, m, a, l)
    val ls = l.size - bi
    val out = List(t) {
        val (i, j, p0) = readLine()!!.split(" ").map { it.toInt() - 1 }
        val p = p0 + 1
        val q = if (p < l.size) p else bi + (p - l.size) % ls
        val ans = l[q][i * m + j]
        if (ans) "1" else "0"
    }
    println(out.joinToString("\n"))
}

fun simGames(n: Int, m: Int, a: BitSet, l: ArrayList<BitSet>): Int {
    val p = HashMap<BitSet, Int>()
    var cur = a
    while (true) {
        p[cur]?.let { return it }
        p[cur] = l.size
        l.add(cur)
        cur = lifeStep(n, m, cur)
    }
}
fun BitSet.same(n: Int, m: Int, i: Int, j: Int, c: Boolean): Boolean {
    if (i !in 0 until n) return false
    if (j !in 0 until m) return false
    return get(i * m + j) == c
}

fun lifeStep(n: Int, m: Int, a: BitSet): BitSet {
    val b = a.clone() as BitSet
    for (i in 0 until n) {
        for (j in 0 until m) {
            val c = a[i * m + j]
            if (a.same(n, m, i - 1, j, c) ||
                a.same(n, m, i + 1, j, c) ||
                a.same(n, m, i, j - 1, c) ||
                a.same(n, m, i, j + 1, c)
            ) {
                b.flip(i * m + j)
            }
        }
    }
    return b
}
