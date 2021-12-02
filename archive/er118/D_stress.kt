import kotlin.math.*
import kotlin.random.*

fun main() {
    val n = 5
    while(true) {
        val a = List(n) { Random.nextInt(0..n) }
        val ans = solveD(n, a)
        var cor = 0L
        val all = ArrayList<List<Int>>()
        for (m in 1 until (1 shl n)) {
            val b = a.filterIndexed { i, _ -> (1 shl i) and m != 0 }
            var mex = 0
            var ok = true
            for (i in b.indices) {
                val bb = b.subList(0, i + 1)
                while (mex in bb) mex++
                if (abs(b[i] - mex) > 1) {
                    ok = false
                    break
                }
            }
            if (ok) {
                cor++
                all += b
            }
        }
        if (ans != cor) {
            println(a)
            println("$cor, all = $all")
            println("$ans - incorrect")
            break
        }

    }
}