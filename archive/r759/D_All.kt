fun main() {
    val n = 6
    val r = HashSet<List<Int>>()
    val q = ArrayDeque<List<Int>>()
    fun add(a: List<Int>) {
        if (r.add(a)) {
            println(a)
            q += a
        }
    }
    add((1..n).toList())
    while (!q.isEmpty()) {
        val a = q.removeFirst()
        for (i in 0 until n) for (j in 0 until n) for (k in 0 until n) if (i != j && i != k && j != k) {
            val b = a.toMutableList()
            val t = b[i]
            b[i] = b[j]
            b[j] = b[k]
            b[k] = t
            add(b)
        }
    }
    println(r.size)
    println((1..n).reduce(Int::times))
}