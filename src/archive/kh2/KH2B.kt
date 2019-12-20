package archive.kh2

fun main() {
    val n = readLine()!!.toLong()
    val a = readLine()!!.split(" ").map { it.toInt() }
    var ans = 0L
    for ((i, k) in a.withIndex()) {
        if (k == 0) continue
        var visits = (i + 1).toLong()
        if (k > 0) visits += (k - 1) * n
        if (visits > ans) ans = visits
    }
    println(ans)
}