package archive.er78

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val l = IntArray(n)
    val r = IntArray(n)
    val et = IntArray(2 * n)
    val ei = IntArray(2 * n)
    repeat(n) { i ->
        val a = readLine()!!.split(" ")
        l[i] = a[0].toInt() - 1
        r[i] = a[1].toInt() - 1
        et[l[i]] = 1
        ei[l[i]] = i
        et[r[i]] = 2
        ei[r[i]] = i
    }
    val m = TreeMap<Int, Int>()
    val p = IntArray(n) { -1 }
    val d = IntArray(n)
    tailrec fun comp(i: Int): Int = if (p[i] < 0) i else comp(p[i])
    var ans = true
    var nc = n
    loop@for (k in 0 until 2 * n) {
        val i = ei[k]
        val ri = r[i]
        when (et[k]) {
            1 -> {
                for ((_, j) in m.headMap(ri)) {
                    val ci = comp(i)
                    val cj = comp(j)
                    if (ci == cj) {
                        ans = false
                        break@loop
                    }
                    if (d[ci] > d[cj]) {
                        p[cj] = ci
                    } else {
                        p[ci] = cj
                        if (d[ci] == d[cj]) d[cj]++
                    }
                    nc--
                }
                m[ri] = i
            }
            2 -> {
                m.remove(ri)
            }
        }
    }
    if (nc != 1) ans = false
    println(if (ans) "YES" else "NO")
}