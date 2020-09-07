package archive.r668

fun main() = System.`in`.bufferedReader().run {
    List(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val s = readLine()!!.toCharArray()
        if (solveA(n, k, s)) "YES" else "NO"
    }.joinToString("\n").let { println(it) }
}

private fun solveA(n: Int, k: Int, s: CharArray): Boolean {
    var c0 = 0
    var c1 = 0
    for (i in 0 until k) {
        var c = '?'
        for (j in i until n step k) {
            if (s[j] == '?') continue
            if (c == '?') {
                c = s[j]
            } else if (c != s[j]) return false
        }
        when (c) {
            '0' -> c0++
            '1' -> c1++
        }
    }
    return c0 <= k / 2 && c1 <= k / 2
}