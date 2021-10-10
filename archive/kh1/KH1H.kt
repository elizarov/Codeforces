package archive.kh1

fun main() {
    val t = readLine()!!.toInt()
    val ans = (1..t).joinToString("\n") { solveSawCase() }
    println(ans)
}

private fun solveSawCase(): String {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.sorted().toIntArray()
    val b = IntArray(n)

    fun saw(m1: Int, m2: Int): Boolean {
        var r1 = m1
        var r2 = m2
        val m = m1 + m2
        val r1f = if (r1 >= r2) 0 else 1
        for (i in 0 until m) {
            if (i % 2 == r1f) {
                b[i] = a[--r1]
            } else {
                b[i] = a[n - m2 + --r2]
            }
            if (i > 0 && b[i] == b[i - 1]) return false
        }
        return true
    }

    fun saw(m: Int): Boolean = saw(m / 2, (m + 1) / 2) || saw((m + 1) / 2, m / 2)

    var l = 0
    var r = n + 1
    while (r - l > 1) {
        val m = (l + r) / 2
        if (saw(m))
            l = m
        else
            r = m
    }
    saw(l)
    return "$l\n${b.take(l).joinToString(" ")}"
}

