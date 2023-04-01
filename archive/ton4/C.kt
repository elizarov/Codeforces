fun main() {
    repeat(readln().toInt()) {
        val (n, c, d) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }
        var ans = solveC(n, c, d, a)
        println(ans)
    }
}

fun solveC(n: Int, c: Int, d: Int, a: List<Int>): Long {
    var cur = n.toLong() * c
    var ans = if (1 in a) cur - c else cur + d
    var p = 0
    for (q in a.sorted()) {
        if (q == p) continue
        cur += (q - p - 1).toLong() * d - c
        ans = minOf(ans, cur)
        p = q
    }
    return ans
}