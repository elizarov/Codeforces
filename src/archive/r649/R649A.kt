package archive.r649

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, x) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }
        println(solveA(n, x, a))
    }
}

fun solveA(n: Int, x: Int, a: List<Int>): Int {
    if (a.sum() % x != 0) return n
    val li = a.indexOfFirst { it % x != 0 }
    val ri = a.indexOfLast { it % x != 0 }
    var ans = if (li >= 0) n - li - 1 else -1
    if (ri >= 0) ans = maxOf(ans, ri)
    return ans
}
