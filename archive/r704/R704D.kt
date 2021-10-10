package archive.r704

fun main() {
    val (a, b, k) = readLine()!!.split(" ").map { it.toInt() }
    val s = solveD(a, b, k)
    if (s == null) {
        println("No")
    } else {
        println("Yes")
        println(s.first)
        println(s.second)
    }
}

fun solveD(a: Int, b: Int, k: Int): Pair<String, String>? {
    if (k == 0) {
        val s = "1".repeat(b) + "0".repeat(a)
        return s to s
    }
    if (a == 0 || b == 0) return null
    val qMax = minOf(b - 1, k)
    val q = maxOf(k + 1 - a, 1)
    if (q > qMax) return null
    val aRem = a - (k + 1 - q)
    val bRem = b - q
    val h = "1".repeat(bRem) + "0".repeat(aRem)
    val x = h + "1" + "0".repeat(k - q) + "1".repeat(q - 1) + "0"
    val y = h + "0".repeat(k + 1 - q) + "1".repeat(q)
    return x to y
}