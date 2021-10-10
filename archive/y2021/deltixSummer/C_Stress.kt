package archive.y2021.deltixSummer

import kotlin.random.*

fun main() {
    val n = 5
    val c = IntArray(n)
    while (true) {
        for (i in 0 until n) c[i] = Random.nextInt(1, 10)
        val sol = solveC(n, c)
        val s = ArrayList<Int>()
        for (i in 0 until n) repeat(c[i]) {
            s.add(if (i % 2 == 0) 1 else -1)
        }
        var cnt = 0L
        for (i in 0 until s.size) for (j in i + 1 until s.size) {
            var b = 0
            var ok = true
            for (k in i..j) {
                b += s[k]
                if (b < 0)  {
                    ok = false
                    break
                }
            }
            if (ok && b == 0) cnt++
        }
        if (sol != cnt) {
            println(n)
            println(c.joinToString(" "))
            println("cnt = $cnt")
            println("sol = $sol")
            return
        }
    }
}