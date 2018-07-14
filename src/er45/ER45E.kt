package er45e

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!.toIntArray()
    val a = readLine()!!.toIntArray()
    println(solveLamps(n, m, k, s, a))
}

fun solveLamps(n: Int, m: Int, k: Int, s: IntArray, a: IntArray): Long {
    val b = BooleanArray(n + 1)
    s.forEach { b[it] = true }
    val lf = IntArray(n + 1)
    for (i in 0..n) {
        if (b[i]) {
            lf[i] = if (i > 0) lf[i - 1] else -1
        } else
            lf[i] = i
    }
    var best = Long.MAX_VALUE
    pl@ for (pwr in k downTo 1) {
        var i = 0
        var cnt = 0
        while (i < n) {
            val j = lf[i]
            if (j < 0) break@pl
            if (j + pwr <= i) break@pl
            i = j + pwr
            cnt++
        }
        val cost = cnt.toLong() * a[pwr - 1]
        best = min(best, cost)
    }
    return if (best == Long.MAX_VALUE) -1L else best
}

fun String.toIntArray(): IntArray {
    var res = IntArray(8)
    var m = 0
    var cur = 0
    var sgn = 1
    var num = false
    val n = length
    for (i in 0..n) {
        val c = if (i < n) get(i) else ' '
        when (c) {
            '-' -> {
                require(!num) { "'-' sign in the middle of the number" }
                require(sgn == 1) { "Extra '-' sign" }
                sgn = -1
            }
            in '0'..'9' -> {
                require(cur <= Integer.MAX_VALUE / 10) { "Integer overflow" }
                cur = cur * 10 + c.toInt() - '0'.toInt()
                require(cur >= 0) { "Integer overflow" }
                num = true
            }
            in '\u0000'..' ' -> {
                require(num || sgn == 1) { "Lone '-' sign" }
                if (num) {
                    if (m >= res.size) res = res.copyOf(res.size * 2)
                    res[m++] = sgn * cur
                    cur = 0
                    sgn = 1
                    num = false
                }
            }
            else -> error("Invalid char '$c' at position $i")
        }
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}

/*

1 0 1

1000000

=> 1000000

 */