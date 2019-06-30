package archive.kh1

fun main() {
    val (n, m) = readInts()
    val a = readInts()
    val q = readInt()
    val ans = List(q) {
        val w = readInts().drop(1) + (m + 1) // sentinel
        if (solveDoors(n, m, a, w)) "YES" else "NO"
    }.joinToString("\n")
    println(ans)
}

private fun solveDoors(n: Int, m: Int, a: List<Int>, w: List<Int>): Boolean {
    var pp = 0
    var i = 0
    for (cp in w) {
        var d = cp - pp - 1
        while (i < n && d >= a[i]) {
            d -= a[i]
            i++
        }
        pp = cp
    }
    return i >= n
}

private fun readInt() = readLine()!!.toInt()
private fun readInts() = readLine()!!.split(" ").map { it.toInt() }