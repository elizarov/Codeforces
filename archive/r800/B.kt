@OptIn(ExperimentalStdlibApi::class)
fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val p = listOf(-1) + readln().split(" ").map { it.toInt() - 1 }
        val l = LongArray(n)
        val r = LongArray(n)
        for (i in 0 until n) {
            val (li, ri) = readln().split(" ").map { it.toLong() }
            l[i] = li
            r[i] = ri
        }
        val c = Array(n) { ArrayList<Int>() }
        for (i in 1 until n) {
            c[p[i]] += i
        }
        var ops = 0
        val dfs = DeepRecursiveFunction<Int, Long> { i ->
            var s = 0L
            for (j in c[i]) {
                s += callRecursive(j)
            }
            if (s < l[i]) {
                ops++
                s = r[i]
            }
            s.coerceAtMost(r[i])
        }
        dfs(0)
        println(ops)
    }
}