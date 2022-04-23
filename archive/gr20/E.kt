fun main() {
    val n = readln().toInt()
    var l = n + n - 1 - 1
    var r = 2000 * n + n - 1
    fun query(w: Int): Int {
        println("? $w")
        return readln().toInt()
    }
    while (l < r - 1) {
        val m = (l + r) / 2
        if (query(m) == 1) {
            r = m
        } else {
            l = m
        }
    }
    var ans = r
    for (k in 2..n) {
        val w = r / k
        val h = query(w)
        if (h != 0) ans = minOf(ans, w * h)
    }
    println("! $ans")
}