package er47

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val g = solveGraph(n, m)
    if (g == null) {
        println("Impossible")
    } else {
        println("Possible")
        g.forEach { (u, v) ->
            println("$u $v")
        }
    }
}

fun solveGraph(n: Int, m: Int): List<Pair<Int, Int>>? {
    if (m < n - 1) return null
    val g = ArrayList<Pair<Int, Int>>(m)
    for (i in 1..n) {
        for (j in i + 1..n) {
            if (gcd(i, j) != 1) continue
            g += Pair(i, j)
            if (g.size == m) return g
        }
    }
    return null
}

tailrec fun gcd(x: Int, y: Int): Int = if (x == 0) y else gcd(y % x, x)

