package archive.r528

fun main(args: Array<String>) {
    val (n, s) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { ArrayList<Int>() }
    repeat(n - 1) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[a].add(b)
        g[b].add(a)
    }
    println(solveTree(n, g, s))
}

fun solveTree(n: Int, g: Array<ArrayList<Int>>, s: Int): Double {
    if (n == 2) return s.toDouble()
    val k = g.count { it.size == 1 }
    return 2.0 * s / k
}
