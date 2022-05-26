import java.util.Arrays

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        val b = readln().split(" ").map { it.toInt() }.toIntArray()
        data class P(val i: Int, val j: Int)
        val res = ArrayList<P>()
        var ok = true
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val a1 = a[i]
                val a2 = a[j]
                val b1 = b[i]
                val b2 = b[j]
                when {
                    a1 <= a2 && b1 <= b2 -> { /*ok*/ }
                    a1 >= a2 && b1 >= b2 -> {
                        a[i] = a2
                        a[j] = a1
                        b[i] = b2
                        b[j] = b1
                        res += P(i, j)
                    }
                    else -> {
                        ok = false
                        break
                    }
                }
            }
        }
        if (!ok) {
            println(-1)
        } else {
            println(res.size)
            res.forEach { println("${it.i + 1} ${it.j + 1}") }
        }
    }
}