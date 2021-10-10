package archive.gr9

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        println(if (solveC(n, a)) "YES" else "NO")
    }
}

fun solveC(n: Int, a: List<Int>): Boolean {
    val b = IntArray(n)
    var k = 0
    b[0] = a[0]
    for (i in 1 until n) {
        while (k > 0 && a[i] > b[k]) k--
        if (k == 0 && a[i] > b[0]) continue
        b[++k] = a[i]
    }
    return k == 0
}