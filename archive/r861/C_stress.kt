import kotlin.random.Random

fun main() {
    val rnd = Random(1)
    while (true) {
        val l = rnd.nextLong(1L, 1000L)
        val r = l + rnd.nextLong(1L, 1000L)
        val sol = solveC(l, r)
        val ans = (l..r).minBy { luck(it) }
        if (luck(sol) != luck(ans)) {
            println("$l $r")
            println("sol = $sol, luck = ${luck(sol)}")
            println("ans = $ans, luck = ${luck(ans)}")
            return
        }
    }
}

private fun luck(x: Long): Int {
    var r = x
    var min = 10
    var max = 0
    while (r != 0L) {
        val d = (r % 10).toInt()
        r /= 10
        min = minOf(d, min)
        max = maxOf(d, max)
    }
    return max - min
}