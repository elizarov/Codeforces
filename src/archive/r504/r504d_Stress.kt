package archive.r504

import java.util.*

fun main(args: Array<String>) {
    val n = 8
    val q = 4
    val rnd = Random(1)
    val a = IntArray(n)
    repeat(10000) {
        do {
            a.fill(0)
            for (x in 1..q) {
                val ofs = rnd.nextInt(n)
                val size = rnd.nextInt(n - ofs) + 1
                a.fill(x, ofs, ofs + size)
            }
        } while (a.indexOf(0) >= 0)
        for (i in 0 until n) {
            if (rnd.nextBoolean()) a[i] = 0
        }
        val ok = try {
            findArray(n, q, a.copyOf())
        } catch(e: Throwable) {
            e.printStackTrace(System.out)
            false
        }
        if (!ok) {
            println("$n $q")
            println(a.joinToString(" "))
            return
        }
    }
    println("OK")
}