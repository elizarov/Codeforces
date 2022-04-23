import kotlin.random.*

fun main() {
    val n = 5
    while(true) {
        val a0 = List(n) { Random.nextInt(1, n) }
        val d = HashMap<List<Int>, Int>()
        val q = ArrayList<List<Int>>()
        fun enqueue(a: List<Int>, dist: Int) {
            if (a in d) return
            q += a
            d[a] = dist
        }
        enqueue(a0, 0)
        var h = 0
        while (h < q.size) {
            val a = q[h++]
            val d1 = d[a]!! + 1
            for (i in 0 until n - 1) for (j in i + 1 until n) {
                val b = a.toMutableList()
                b[i] = b[j].also { b[j] = b[i] }
                enqueue(b, d1)
            }
        }
        val maxA = q.last()
        val maxD = d[maxA]!!
        val solA = solveF1(n, a0).toList()
        val solD = d[solA]!!
        if (solD != maxD) {
            println(n)
            println(a0.joinToString(" "))
            println("solA: ${solA.joinToString(" ")}")
            println("solD: $solD")
            println("maxA: ${maxA.joinToString(" ")}")
            println("maxD: $maxD")
            return
        }
    }
}