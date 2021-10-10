package archive.r655

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toLong() }
    val s = LongArray(2)
    val p = LongArray(2)
    for (i in 0 until n) s[i % 2] += a[i]
    var ans = 0L
    for (i in 0 until n) {
        var t = s[i % 2] + p[1 - i % 2]
        if (i == 0 && n > 1) t += a[n - 1]
        if (t > ans) ans = t
        s[i % 2] -= a[i]
        p[i % 2] += a[i]
    }
    println(ans)
}