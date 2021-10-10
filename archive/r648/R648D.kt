package archive.r648

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val a = Array(n) { readLine()!!.toCharArray() }
        println(if (solveD(n, m, a)) "Yes" else "No")
    }
}

fun solveD(n: Int, m: Int, a: Array<CharArray>): Boolean {
    fun block(i: Int, j: Int): Boolean {
        if (i !in 0 until n || j !in 0 until m) return true
        when (a[i][j]) {
            'G' -> return false
            'B', '#' -> return true
        }
        a[i][j] = '#'
        return true
    }
    for (i in 0 until n) for (j in  0 until m) {
        if (a[i][j] == 'B') {
            if (!block(i - 1, j) ||
                !block(i + 1, j) ||
                !block(i, j + 1) ||
                !block(i, j - 1)) return false
        }
    }
    fun scan(i: Int, j: Int) {
        if (i !in 0 until n || j !in 0 until m) return
        when (a[i][j]) {
            '#', '+' -> return
            'G', '.' -> a[i][j] = '+'
            'B' -> error("!")
        }
        scan(i - 1, j)
        scan(i + 1, j)
        scan(i, j - 1)
        scan(i, j + 1)
    }
    scan(n - 1, m - 1)
    for (i in 0 until n) for (j in  0 until m) {
        if (a[i][j] == 'G') return false
    }
    return true
}
