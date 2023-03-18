fun main() {
    val n = 2
    val m = 2 * n
    val q = IntArray(m)
    val f = BooleanArray(m)
    fun isGood(i: Int, k: Int, p: Int, s: Int): Boolean {
        if (i == m) {
            if (k != n) return true
            return p == s
        }
        val g1 = isGood(i + 1, k, p, s + q[i])
        f[i] = true
        val g2 = isGood(i + 1, k + 1, p * q[i], s)
        f[i] = false
        return g1 && g2
    }
    fun scan(i: Int) {
        if (i == m) {
            if (isGood(0, 0, 1, 0)) {
                println(q.joinToString(" "))
            }
            return
        }
        for (x in -4..4) {
            q[i] = x
            scan(i + 1)
        }
    }
    scan(0)
}