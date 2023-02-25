fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        println(if (solveA(n, a)) "Yes" else "No")
    }
}

fun solveA(n: Int, a: List<Int>): Boolean {
    for (i in 0 until n) for (j in i + 1 until n) {
        if (gcd(a[i], a[j]) <= 2) return true
    }
    return false
}

tailrec fun gcd(x: Int, y: Int): Int =
    if (y == 0) x else gcd(y, x % y)
