package er45d

fun main(args: Array<String>) {
    val (n, a, b) = readLine()!!.split(" ").map { it.toInt() }
    val res = when {
        a > 1 && b == 1 -> buildAdj(n, a)
        a == 1 && b > 1 -> buildAdj(n, b).invert()
        a == 1 && b == 1 && (n == 1 || n > 3) -> buildAdj(n, a)
        else -> {
            println("NO")
            return
        }
    }
    println("YES")
    res.forEach { println(it.joinToString(separator = "")) }
}


fun buildAdj(n: Int, a: Int): Array<IntArray> {
    val adj = Array(n) { IntArray(n) }
    for (i in 0 until (n - a)) {
        adj[i][i + 1] = 1
        adj[i + 1][i] = 1
    }
    return adj
}

fun Array<IntArray>.invert(): Array<IntArray> {
    val adj = this
    val n = adj.size
    return Array(n) { i -> IntArray(n) { j ->
        if (i == j) 0 else 1 - adj[i][j]
    } }
}
