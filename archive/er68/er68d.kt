package archive.er68

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        println(if (solveAliceBob(n, k)) "Alice" else "Bob")
    }
}

fun solveAliceBob(n: Int, k: Int): Boolean {
    if (k % 3 != 0) {
        return n % 3 != 0
    } else {
        val r = n % (k + 1)
        if (r == k) return true
        return r % 3 != 0
    }
}
