fun main() {
    val n = 12
    val a = IntArray(2 * n)
    val c = IntArray(n + 1)
    val f = IntArray(n + 1)
    fun find(i: Int): Boolean {
        if (i == 2 * n) {
            println(a.joinToString(" "))
            return true
        }
        for (j in n downTo 1) if (c[j] < 2) {
            if (c[j] == 0 || (f[j] - i) % j == 0) {
                if (c[j] == 0) f[j] = i
                c[j]++
                a[i] = j
                if (find(i + 1)) return true
                c[j]--
            }
        }
        return false
    }
    find(0)
}