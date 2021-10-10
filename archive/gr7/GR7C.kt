package archive.gr7

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val p = readLine()!!.split(" ").map { it.toInt() }
    val q = MutableList(k) { -1 }
    var sum = 0L
    for ((i, pi) in p.withIndex()) {
        if (pi >= n - k + 1) {
            q[pi - n + k - 1] = i
            sum += pi
        }
    }
    q.sort()
    var ans = 1L
    for (i in 0..k - 2) {
        ans *= q[i + 1] - q[i]
        ans %= 998244353
    }
    println("$sum $ans")
}