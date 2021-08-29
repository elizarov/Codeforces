package archive.r651

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.split(" ").map { it.toInt() }
    fun c(i0: Int, i1: Int, m: Int, lim: Int): Boolean {
        var c = 0
        var i = i0
        while (i < i1) {
            if (a[i] <= m) {
                c++
                if (c >= lim) return true
                i += 2
            } else {
                i++
            }
        }
        return false
    }
    var l = 0
    var r = a.maxOrNull()!!
    val k0 = (k + 1) / 2
    val k1 = k / 2
    val io = k % 2
    val ie = 1 - io
    while (l < r - 1) {
        val m = (l + r) / 2
        if (c(0, n - ie, m, k0) || c(1, n - io, m, k1)) {
            r = m
        } else {
            l = m
        }
    }
    println(r)
}