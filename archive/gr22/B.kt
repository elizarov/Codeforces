fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val s = readln().split(" ").map { it.toInt() }
        println(if (solveB(s, n, k)) "Yes" else "No")
    }
}

fun solveB(s: List<Int>, n: Int, k: Int): Boolean {
    var prev = Int.MAX_VALUE
    for (i in k - 2 downTo 0) {
        val next = s[i + 1] - s[i]
        if (next > prev) return false
        prev = next
    }
    val rem = n - k + 1
    val last = divCeil(s[0], rem)
    return prev >= last
}

fun divCeil(x: Int, y: Int): Int =
    if (x >= 0) (x + y - 1) / y else x / y


