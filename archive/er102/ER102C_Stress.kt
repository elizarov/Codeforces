fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = IntArray(n)
    for (i in 0 until k) a[i] = i
    for (i in k until n) a[i] = (k - 2) - (i - k)
    val p = IntArray(k)
    fun invs(): Int {
        var c = 0
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (p[a[i]] > p[a[j]]) c++
            }
        }
        return c
    }
    for (i in 0 until k) p[i] = i
    val initial = invs()
    println("initial = $initial")
    val u = BooleanArray(k)
    fun scan(i: Int): Boolean {
        if (i == k) return invs() <= initial
        for (j in k - 1 downTo 0) if (!u[j]) {
            u[j] = true
            p[i] = j
            if (scan(i + 1)) return true
            u[j] = false
        }
        return false
    }
    println("Found - ${scan(0)}")
    println("p = ${p.toList()}")
    println("count = ${invs()}")
}