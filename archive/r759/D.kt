fun main() {
    data class IS(val c: Long, val a: IntArray)
    fun invSort(a: IntArray): IS {
        val n = a.size
        if (n <= 1) return IS(0, a)
        val l = invSort(a.sliceArray(0 until n / 2))
        val r = invSort(a.sliceArray(n / 2 until n))
        var c = l.c + r.c
        var i = 0
        var j = 0
        var k = 0
        while (k < n) {
            if (i < l.a.size && j < r.a.size && l.a[i] <= r.a[j] || j >= r.a.size) {
                a[k++] = l.a[i++]
            } else {
                a[k++] = r.a[j++]
                c += l.a.size - i
            }
        }
        return IS(c, a)
    }
    val ans = List(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        val (inv, b) = invSort(a)
        val same = (1 until n).any { i -> b[i] == b[i - 1] }
        if (same || inv % 2 == 0L) "YES" else "NO"
    }
    println(ans.joinToString("\n"))
}