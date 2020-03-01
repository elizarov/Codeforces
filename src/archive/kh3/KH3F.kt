package archive.kh3

import java.util.*

fun main() {
    repeat(readLine()!!.toInt()) {
        readLine() // skip empty line
        solveCase()
    }
}

private data class Mv(val i: Int, val a: Int, val b: Int, var t: Int = 0) : Comparable<Mv> {
    override fun compareTo(other: Mv): Int = if (b != other.b) b.compareTo(other.b) else i.compareTo(other.i)
}

private fun solveCase() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val d = Array(n) { i ->
        readLine()!!.split(" ").map { it.toInt() }.let { (a, b) -> Mv(i, a, b) }
    }
    d.sortBy { it.a }
    var t = 0
    val w = TreeSet<Mv>()
    fun advance(to: Int) {
        while (t < to && !w.isEmpty()) {
            repeat(minOf(w.size, m)) {
                val v = w.first()
                v.t = t
                w -= v
            }
            t++
        }
        t = to
    }
    for (v in d) {
        advance(v.a)
        w += v
    }
    advance(Int.MAX_VALUE)
    d.sortBy { it.i }
    println(maxOf(0, d.map { it.t - it.b }.max()!!))
    println(d.joinToString(" ") { it.t.toString() })
}