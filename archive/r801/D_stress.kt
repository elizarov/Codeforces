import kotlin.random.*

fun main() {
    val n = 6
    while (true) {
        val seed = Random.nextLong()
        val g0 = generate(seed, n, (0 until n).toList())
        val a0 = solveD(n, g0)
        repeat(10) {
            val t = (0 until n).shuffled()
            val g1 = generate(seed, n, t)
            val a1 = solveD(n, g1)
            if (a1 != a0) {
                println("!!! $a1 != $a0")
                println(n)
                for (i in 0 until n) for (j in g1[i]) if (j > i) {
                    println("${i + 1} ${j + 1}")
                }
                println("---")
                println(n)
                for (i in 0 until n) for (j in g0[i]) if (j > i) {
                    println("${i + 1} ${j + 1}")
                }
                return
            }
        }
    }
}

private fun generate(
    seed: Long,
    n: Int,
    t: List<Int>
): Array<ArrayList<Int>> {
    val g = Array(n) { ArrayList<Int>() }
    fun add(i: Int, j: Int) {
        g[t[i]] += t[j]
        g[t[j]] += t[i]
    }
    fun Random.gen(i: Int, k: Int) {
        if (k == 1) return
        when (nextInt(1..((k - 1).coerceAtMost(2)))) {
            1 -> {
                add(i, i + 1)
                gen(i + 1, k - 1)
            }
            2 -> {
                val k1 = nextInt(1..k - 2)
                val k2 = k - k1 - 1
                add(i, i + 1)
                add(i, i + 1 + k1)
                gen(i + 1, k1)
                gen(i + 1 + k1, k2)
            }
        }
    }
    Random(seed).gen(0, n)
    return g
}