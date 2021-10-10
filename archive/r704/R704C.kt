package archive.r704

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!
    val t = readLine()!!
    val l = IntArray(m + 1)
    l[0] = 0
    var i = 0
    for (j in 0 until m) {
        while (s[i] != t[j]) i++
        l[j + 1] = ++i
    }
    val r = IntArray(m + 1)
    r[0] = 0
    i = 0
    for (j in 0 until m) {
        while (s[n - 1 - i] != t[m - 1 - j]) i++
        r[j + 1] = ++i
    }
    var ans = 0
    for (j in 1 until m) {
        ans = maxOf(ans, n - r[m - j] - l[j] + 1)
    }
    println(ans)
}