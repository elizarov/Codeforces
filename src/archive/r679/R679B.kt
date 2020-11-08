package archive.r679

import java.io.*

fun main() = System.`in`.bufferedReader().run {
    val n = readLine()!!.toInt()
    val a = solveB(n)
    if (a == null) {
        println("NO")
    } else {
        println("YES")
        println(a.joinToString(" "))
    }
}

private fun BufferedReader.solveB(n: Int): IntArray? {
    data class Pr(val i: Int, val b: Int)
    val st = ArrayList<Int>()
    val pr = ArrayList<Pr>()
    pr.add(Pr(-2, Int.MAX_VALUE))
    var i = 0
    val a = IntArray(n)
    repeat(2 * n) {
        val e = readLine()!!
        when (e[0]) {
            '+' -> {
                st.add(i++)
            }
            '-' -> {
                val x = e.substringAfter(" ").toInt()
                if (st.isEmpty()) return null
                val j = st.removeLast()
                a[j] = x
                val pl = pr.last()
                if (st.size <= pl.i && x < pl.b) return null
                if (pl.i > st.lastIndex) {
                    pr.removeLast()
                    if (pr.last().i < st.lastIndex) pr.add(Pr(st.lastIndex, pl.b))
                }
                while (x >= pr.last().b) pr.removeLast()
                if (pr.last().i < st.lastIndex) pr.add(Pr(st.lastIndex, x))
            }
        }
    }
    return a
}