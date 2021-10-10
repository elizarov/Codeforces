package archive.r648

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val b = readLine()!!.split(" ").map { it.toInt() }
        println(if (solveB(n, a, b)) "Yes" else "No")
    }
}

fun solveB(n: Int, a: List<Int>, b: List<Int>): Boolean {
    if (a.sorted() == a) return true
    if (b.all { it == 0 } || b.all { it == 1 }) return false
    return true
}

