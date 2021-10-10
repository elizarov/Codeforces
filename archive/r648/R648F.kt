package archive.r648

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val b = readLine()!!.split(" ").map { it.toInt() }
        println(if (solveF(n, a, b)) "Yes" else "No")
    }
}

fun solveF(n: Int, a: List<Int>, b: List<Int>): Boolean {
    if (n % 2 != 0) {
        val i = n / 2
        if (a[i] != b[i]) return false
    }
    return true
}
