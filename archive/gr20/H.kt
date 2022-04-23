fun main() {
    val (n, q) = readln().split(" ").map { it.toInt() }
    val a = readln().map { it.digitToInt() }
    val rt = IntArray(n)
    val lt = IntArray(n)
    val ft = IFenwickTree(n)
    var j = -1
    for (i in 0 until n) {
        if (i > 0 && a[i] != a[i - 1]) {
            val len = i - j - 1
            ft.update(j + 1, len - 1)
            j = i - 1
        }
        lt[i] = j
    }
    j = n
    for (i in n - 1 downTo 0) {
        if (i < n - 1 && a[i] != a[i + 1]) j = i + 1
        rt[i] = j
    }
    val ans = List(q) {
        val (l, r) = readln().split(" ").map { it.toInt() - 1 }
        val l2 = rt[l]
        if (l2 > r) {
            r - l + 1
        } else {
            val r2 = lt[r]
            if (r2 < l2) {
                check(r2 == l2 - 1)
                r - l
            } else {
                l2 - l - 1 + r - r2 - 1 + ft.sum(l2, r2) + 1
            }
        }
    }
    println(ans.joinToString("\n"))
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

    fun sum(fromIndex: Int, toIndex: Int): Int = // inclusive
        if (toIndex < fromIndex) 0 else
            sum(toIndex) - sum(fromIndex - 1)

    fun update(index: Int, delta: Int) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}