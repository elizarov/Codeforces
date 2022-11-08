@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val n = readln().toInt()
    val p = listOf(-1) + readln().split(" ").map { it.toInt() - 1 }
    val c = Array(n) { ArrayList<Int>() }
    for (i in 1 until n) c[p[i]].add(i)
    data class R(val s0: Int, val s1: Int)
    fun R.best() = maxOf(s0, s1)
    val f = DeepRecursiveFunction<Int, R> { i ->
        val cr = c[i].map { callRecursive(it) }
        val s0 = cr.sumOf { it.best() }
        val s1 = 1 + (cr.maxOfOrNull { it.s1 } ?: 0)
        R(s0, s1)
    }
    println(f(0).best())
}