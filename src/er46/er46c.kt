package er46

import kotlin.math.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val es = ArrayList<Evt>(2 * n + 1)
    repeat(n) {
        val (l, r) = readLine()!!.split(" ").map { it.toLong() }
        es += Evt(l, 1)
        es += Evt(r, -1)
    }
    es.sort()
    var prev = Evt(Long.MIN_VALUE, 0)
    es += Evt(Long.MAX_VALUE, 0)
    val cnt = LongArray(n + 1)
    var total = 0
    var cmax = 0
    for (e in es) {
        if (e.x > prev.x) {
            val d = e.x - prev.x - 1
            cnt[total] += d
            cnt[cmax] += 1L
            cmax = total
        }
        total += e.c
        cmax = max(cmax, total)
        prev = e
    }
    for (i in 1..n) {
        if (i > 1) print(" ")
        print(cnt[i])
    }
    println()
}

class Evt(val x: Long, val c: Int) : Comparable<Evt> {
    override fun compareTo(other: Evt): Int {
        val d = x.compareTo(other.x)
        if (d != 0) return d
        return other.c - c
    }
}