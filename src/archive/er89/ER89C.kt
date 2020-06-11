package archive.er89

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val a = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
        val z = (n + m - 3) / 2
        val c = Array(2) { IntArray(z + 1) }
        for (i in 0 until n) for (j in 0 until m) {
            val k = minOf(i + j, m + n - i - j - 2)
            if (k <= z) c[a[i][j]][k]++
        }
        var s = 0
        for (k in 0..z) s += minOf(c[0][k], c[1][k])
        println(s)
    }
}