import kotlin.math.abs
import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val n = 6
    val rnd = Random(1)
    while(true) {
        val a = List(n) { rnd.nextInt(1..10) }
        val b = Array(3) { ArrayList<Int>() }
        var best = 0
        var sol = emptyList<List<Int>>()
        fun place(i: Int) {
            if (i == n) {
                for (j in 0 until 3) if (b[j].isEmpty()) return
                var opt = Int.MAX_VALUE
                for (b0 in b[0]) for (b1 in b[1]) for (b2 in b[2]) {
                    val cur = abs(b0 - b1) + abs(b1 - b2)
                    opt = minOf(opt, cur)
                }
                if (opt > best) {
                    best = opt
                    sol = b.map { it.toList() }
                }
                return
            }
            for (j in 0 until 3) {
                b[j].add(a[i])
                place(i + 1)
                b[j].removeLast()
            }
        }
        place(0)
        val ans = solveC(n, a)
        if (best != ans) {
            println("ans  = $ans")
            println("best = $best")
            println(n)
            println(a.sorted().joinToString(" "))
            println("---")
            sol.forEach { println(it.sorted().joinToString(" ")) }
            return
        }
    }
}