package archive.r517

import java.util.*

fun main(args: Array<String>) {
    val (a, b) = readLine()!!.split(" ").map { it.toLong() }
    var sp = 0L
    val p = TreeSet<Int>()
    var i = 1
    while (sp + i <= a + b) {
        p += i
        sp += i
        i++
    }
    val q = mutableListOf<Int>()
    var sq = 0L
    while (sp > a) {
        val r = p.floor((sp - a).toInt()) ?: p.first()!!
        p -= r
        sp -= r
        if (sq + r <= b) {
            q += r
            sq += r
        }
    }
    println(p.size)
    println(p.joinToString(" "))
    println(q.size)
    println(q.joinToString(" "))
}
