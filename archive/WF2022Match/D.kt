fun main() {
    repeat(readln().toInt()) {
        readln()
        val (n, k) = readln().split(" ").map { it.toInt() }
        val g = List(n) { HashSet<Int>() }
        repeat(n - 1) {
            val (u, v) = readln().split(" ").map { it.toInt() - 1 }
            g[u] += v
            g[v] += u
        }
        var a = g.withIndex().filter { (i, c) -> c.size <= 1 }.map { it.index }.toSet()
        val rem = (0 until n).toMutableSet()
        for (op in 1..k) {
            rem -= a
            val b = HashSet<Int>()
            for (i in a) for (j in g[i]) {
                g[j] -= i
                if (g[j].size <= 1 && j in rem) b += j
            }
            a = b
            if (rem.isEmpty()) break
        }
        println(rem.size)
    }
}