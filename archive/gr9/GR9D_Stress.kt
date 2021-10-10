package archive.gr9

import kotlin.random.*

fun main() {
    val n = 7
    var maxA = emptyList<Int>()
    var maxB = emptyList<Int>()
    repeat(1000000) {
        val a0 = List(n) { Random.nextInt(0..n) }
        val b = solveD(n, a0)
        if (b.size > maxB.size) {
            maxA = a0
            maxB = b
        }
        val a = a0.toIntArray()
        val p = IntArray(n + 1)
        for (i in 0 until n) {
            p[a[i]]++
        }
        fun mex(): Int {
            var t = 0
            while (p[t] > 0) t++
            return t
        }
        fun set(i: Int, x: Int) {
            p[a[i]]--
            a[i] = x
            p[x]++
        }
        for (z in b) {
            set(z - 1, mex())
        }
        for (i in 1 until n) {
            if (a[i] < a[i - 1]) {
                println("!!!")
                println(a0.joinToString(" "))
                println(b.size)
                println(b.joinToString(" "))
                println(a.joinToString(" "))
                return
            }
        }
    }
    println("OK")
    println(maxA.joinToString(" "))
    println(maxB.size)
    println(maxB.joinToString(" "))
}