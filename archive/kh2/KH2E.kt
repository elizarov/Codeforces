package archive.kh2

import java.lang.StringBuilder
import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }
    val d = a.withIndex().groupBy { it.value }
    val s = StringBuilder(n).apply { repeat(n) { append('B') } }
    val pr = TreeMap<Int, Int>()
    val pg = TreeMap<Int, Int>()
    for (i in 1..n) {
        val list = d[i] ?: break
        if (list.size != 2) break
        val rn = pr.insert(list[0])
        val gn = pg.insert(list[1])
        if (rn != gn) break
        s[list[0].index] = 'R'
        s[list[1].index] = 'G'
    }
    println(s)
}

private fun TreeMap<Int, Int>.insert(iv: IndexedValue<Int>): Int? {
    val next = tailMap(iv.index).values.firstOrNull()
    put(iv.index, iv.value)
    return next
}
