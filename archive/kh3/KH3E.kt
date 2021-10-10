package archive.kh3

fun main() {
    repeat(readLine()!!.toInt()) {
        readLine() // skip empty line
        solveCase()
    }
}

private fun solveCase() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val gf = IntArray(n) { -1 }
    val gy = IntArray(2 * n)
    val gn = IntArray(2 * n)
    var ec = 0
    fun edge(x: Int, y: Int) {
        gy[ec] = y
        gn[ec] = gf[x]
        gf[x] = ec++
    }
    repeat(n - 1) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        edge(x, y)
        edge(y, x)
    }
    val v = BooleanArray(n)
    val q = IntArray(n)
    var h = 0
    var t = 0
    fun enqueue(x: Int) {
        q[t++] = x
        v[x] = true
    }
    enqueue(0)
    var lc = 1
    while (lc < k && h < t) {
        val x = q[h++]
        var i = gf[x]
        var cc = 0
        while (i >= 0 && lc < k) {
            val y = gy[i]
            if (!v[y]) {
                enqueue(y)
                if (cc++ > 0) lc++
            }
            i = gn[i]
        }
        if (x == 0 && cc == 1) lc++
    }
    if (lc < k) {
        println("No")
        return
    }
    println("Yes")
    println(v.count { it })
    println(v.withIndex().filter { it.value }.joinToString(" ") { (it.index + 1).toString() })
}