package archive.r648

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toLong() }.toLongArray()
    println(solveE(n, a))
}

fun solveE(n: Int, a: LongArray): Long {
    if (n <= 3) return a.fold(0L) { x, y -> x or y }
    var best = 0L
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val pa = a[i] or a[j]
            for (k in j + 1 until n) {
                val ans = pa or a[k]
                if (ans > best) best = ans
            }
        }
    }
    return best
}