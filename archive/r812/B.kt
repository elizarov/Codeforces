fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        println(if (solveB(n, a)) "YES" else "NO")
    }
}

fun solveB(n: Int, a: List<Int>): Boolean {
    var i = 0
    while (i < n - 1 && a[i + 1] >= a[i]) i++
    if (i >= n - 1) return true
    while (i < n - 1 && a[i + 1] <= a[i]) i++
    return i >= n - 1
}
