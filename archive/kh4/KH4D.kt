package archive.kh4

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val e = List(m) {
            val (u, v, w) = readLine()!!.split(" ").map { it.toInt() }
            Tun(u - 1, v - 1, w)
        }
        val a = solveDungeon(n, e)
        if (a != null) {
            println("YES")
            println(a.joinToString(" "))
        } else {
            println("NO")
        }
    }
}

private class Tun(val u: Int, val v: Int, val w: Int)

private fun solveDungeon(n: Int, e: List<Tun>): IntArray? {
    val a = IntArray(n)
    for (t in e.sortedByDescending { it.w }) {
        if (a[t.u] == 0) a[t.u] = t.w
        if (a[t.v] == 0) a[t.v] = t.w
        if (t.w != minOf(a[t.u], a[t.v])) return null
    }
    return a
}
