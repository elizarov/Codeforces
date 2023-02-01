fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        val ans = solveA(n, a)
        println(if (ans) "Yes" else "No")
    }
}

fun solveA(n: Int, a: IntArray): Boolean {
    if (a.sumOf { it.toLong() } != 0L) return false
    if (a.all { it == 0 }) return true
    if (a[0] <= 0) return false
    var p = 0
    var q = a.indexOfFirst { it < 0 }
    while (q < n) { // p < q && a[p] > 0 && a[q] < 0
        val prev = q
        if (a[p] > -a[q]) {
            a[p] += a[q]
            a[q] = 0
        } else {
            a[q] += a[p]
            a[p] = 0
        }
        while (q < n && a[q] >= 0) q++
        while (p < q && a[p] <= 0) p++
        if (p > prev && p < n) return false
    }
    return p == n && q == n
}
