package archive.goodbye2018

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val a = IntArray(100)
    val b = IntArray(100)
    var k = 0
    var t = 2
    var rem = n
    while (t * t <= rem) {
        if (rem % t == 0) {
            a[k] = t
            do {
                rem /= t
                b[k]++
            } while(rem % t == 0)
            k++
        }
        t++
    }
    if (rem > 1) {
        a[k] = rem
        b[k] = 1
        k++
    }

    val c = IntArray(100)
    val ans = TreeSet<Long>()

    fun find(i: Int, s: Int) {
        if (i >= k) {
            val q = n / s
            ans.add(((q - 1L) * s) * q / 2 + q)
            return
        }
        var ss = s
        for (j in 0..b[i]) {
            c[i] = j
            find(i + 1, ss)
            ss *= a[i]
        }
    }

    find(0, 1)
    println(ans.joinToString(" "))
}