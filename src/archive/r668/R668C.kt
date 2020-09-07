package archive.r668

fun main() = System.`in`.bufferedReader().run {
    class Q(val x: Int, val y: Int, var ans: Int = 0)
    val (n, qn) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.split(" ").map { it.toInt() }
    val qs = Array(qn) { readLine()!!.split(" ").map { it.toInt() }.let { Q(it[0], it[1]) } }
    val ft = IFenwickTree(n)
    var j = 0
    for (q in qs.sortedByDescending { it.y }) {
        val qj = n - q.y
        while (j < qj) {
            val d = j + 1 - a[j]
            if (d >= 0) {
                val all = ft.sum(j - 1)
                var l = -1
                var r = j + 1
                while (l < r - 1) {
                    val m = (l + r) / 2
                    val cur = all - ft.sum(m - 1)
                    if (cur >= d) {
                        l = m
                    } else {
                        r = m
                    }
                }
                if (l >= 0) ft.update(l, 1)
            }
            j++
        }
        q.ans = ft.sum(q.x, j - 1)
    }
    println(qs.joinToString("\n") { it.ans.toString() })
}

private class IFenwickTree(n: Int) {
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