import kotlin.random.Random

fun main() {
    val m = 4
    val limit = 1 shl (m - 2)
    val n = 3
    val a = IntArray(n)
    val rnd = Random(1)
    var checked = 0
    while (true) {
        checked++
        if (checked % 10000 == 0) {
            println("Checked $checked")
        }
        for (i in a.indices) {
            a[i] = rnd.nextInt(limit)
        }
        val b = rnd.nextInt(limit)
        val best = calcBestE(n, a, m)
        val sol = solveE(b, best, m)
        val actual = solveActualE(a, b)
        if (actual != sol) {
            println("!!!")
            println(a.joinToString(" "))
            println(b)
            println("sol = $sol")
            println("actual = $actual")
            return
        }
    }
}

fun solveActualE(a: IntArray, b: Int): Int {
    fun find(i: Int, rem: Int, cur: Int): Int {
        if (i == a.size) {
            return cur.countOneBits()
        }
        var max = 0
        for (j in 0..rem) {
            val alt = find(i + 1, rem - j, cur or (a[i] + j))
            max = maxOf(max, alt)
        }
        return max
    }
    return find(0, b, 0)
}
