package archive.er83

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    println(solveCompress(n, a))
}

fun solveCompress(n: Int, a: IntArray): Int {
    val d = Array(n) { l -> Array(n - l) { HashSet<Int>() } }
    for (i in 0 until n) d[0][i].add(a[i])
    for (l in 1 until n) {
        for (i in 0 until n - l) {
            val j = i + l
            for (k in i until j) {
                val s1 = d[k - i][i]
                val s2 = d[j - k - 1][k + 1]
                for (x in s1) if (x in s2) d[l][i].add(x + 1)
            }
        }
    }
    val m = IntArray(n + 1) { it }
    for (j in 0 until n) {
        for (i in 0..j) {
            if (d[j - i][i].isNotEmpty()) m[j + 1] = minOf(m[j + 1], m[i] + 1)
        }
    }
    return m[n]
}