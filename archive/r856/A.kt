fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val aa = readln().split(" ")
        println(if (solveA(n, aa)) "YES" else "NO")
    }
}

private fun solveA(n: Int, aa: List<String>): Boolean {
    for (i in 1 .. n / 2) {
        val (a, b) = aa.filter { it.length == i }
        if (a.reversed() != b) return false
    }
    return true
}