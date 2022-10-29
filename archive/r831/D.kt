fun main() {
    repeat(readln().toInt()) {
        val (n, m, k) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() - 1 }
        val r = n * m - 4
        val b = IntArray(k)
        for (i in 0 until k) b[a[i]] = i
        val ft = IFenwickTree(k)
        var ok = true
        for (i in k - 1 downTo 0) {
            val j = b[i]
            val pos = j - ft.sum(j - 1)
            if (pos > r) {
                ok = false
                break
            }
            ft.update(j, 1)
        }
        println(if (ok) "YA" else "TIDAK")
    }
}

class IFenwickTree(n: Int) {
    val a = IntArray(n)

    fun sum(toIndex: Int): Int { // inclusive
        var i = toIndex
        var sum = 0
        while (i >= 0) {
            sum += a[i]
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun update(index: Int, delta: Int) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}