fun main() {
    val (n, m, q) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) { IntArray(m) }
    val cr = Array(n + 1) { IntArray(m + 1) }
    val cd = Array(n + 1) { IntArray(m + 1) }
    var sum  = 0L
    var free = n * m
    fun updateDown(i: Int, j: Int) {
        cr[i][j] = cd[i][j + 1] + 1
        cd[i][j] = cr[i + 1][j] + 1
        sum += cd[i][j] + cr[i][j]
    }
    for (i in n - 1 downTo 0) for (j in m - 1 downTo 0) {
        updateDown(i, j)
    }
    fun updateUp(x: Int, y: Int) {
        var i: Int = x
        var j: Int = y
        while (true) {
            if (--i < 0 || a[i][j] == 1) break
            sum -= cd[i][j]
            cd[i][j] = cr[i + 1][j] + 1
            sum += cd[i][j]
            if (--j < 0 || a[i][j] == 1) break
            sum -= cr[i][j]
            cr[i][j] = cd[i][j + 1] + 1
            sum += cr[i][j]
        }
        i = x
        j = y
        while (true) {
            if (--j < 0 || a[i][j] == 1) break
            sum -= cr[i][j]
            cr[i][j] = cd[i][j + 1] + 1
            sum += cr[i][j]
            if (--i < 0 || a[i][j] == 1) break
            sum -= cd[i][j]
            cd[i][j] = cr[i + 1][j] + 1
            sum += cd[i][j]
        }
    }
    repeat(q) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        if (a[x][y] == 0) {
            a[x][y] = 1
            sum -= cr[x][y] + cd[x][y]
            free--
            cr[x][y] = 0
            cd[x][y] = 0
        } else {
            a[x][y] = 0
            free++
            updateDown(x, y)
        }
        updateUp(x, y)
        println(sum - free)
    }
}