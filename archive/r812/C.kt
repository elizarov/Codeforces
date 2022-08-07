import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val p = solveC(n)
        if (p == null) {
            println(-1)
        } else {
            println(p.joinToString(" "))
        }
    }
}

fun solveC(n: Int): IntArray? {
    val p = IntArray(n)
    val u = BooleanArray(n)
    for (i in n - 1 downTo 0) {
        var m = sqrt((i + n - 1).toDouble()).toInt()
        while (true) {
            val z = m * m - i
            if (z < 0) return null
            if (!u[z]) {
                u[z] = true
                p[i] = z
                break
            }
            m--
        }
    }
    return p
}