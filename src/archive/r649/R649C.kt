package archive.r649

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }
    val b = solveC(n, a)
    if (b == null) {
        println(-1)
    } else {
        println(b.joinToString(" "))
    }
}

fun solveC(n: Int, a: List<Int>): IntArray? {
    val b = IntArray(n)
    val q = IntArray(n)
    var h = 0
    var t = 0
    for (i in n - 1 downTo 0) {
        val p = if (i > 0) a[i - 1] else 0
        if (a[i] > p) {
            b[i] = p
            for (j in b[i] + 1 until a[i]) q[t++] = j
            continue
        }
        if (h < t) {
            b[i] = q[h++]
        } else {
            b[i] = n + 1
        }
    }
    return b
}
