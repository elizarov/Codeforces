package archive.r398
// Solution for http://codeforces.com/problemset/problem/767/C
fun main() {
    val n = readLine()!!.toInt()
    val a = IntArray(n + 1)
    val t = IntArray(n + 1)
    for (i in 1..n) {
        val (ai, ti) = readLine()!!.splitToIntArray()
        a[i] = ai
        t[i] = ti
    }
    println(Garland(a, t).solve()?.joinToString(" ") ?: "-1")
}

class Garland(val a: IntArray, val t: IntArray) {
    private val total = t.sum()
    private val expected = total / 3
    private val children =
        a.asSequence().withIndex().drop(1).groupBy({ it.value }, { it.index })
    private val result = ArrayList<Int>()

    fun solve(): List<Int>? {
        if (total % 3 != 0) return null
        dfs(children.getValue(0).single())
        return result.takeIf { it.size == 2 }
    }

    private fun dfs(i: Int): Int {
        var iSum = t[i]
        val list = children[i] ?: return iSum
        for (j in list) {
            val jSum = dfs(j)
            if (jSum == expected && result.size < 2) {
                result += j
            } else {
                iSum += jSum
            }
        }
        return iSum
    }
}

private fun String.splitToIntArray(): IntArray {
    val n = length
    if (n == 0) return IntArray(0) // EMPTY
    var res = IntArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0
        var neg = false
        var c = get(i) // expecting number, IOOB if there is no number
        if (c == '-') {
            neg = true
            i++
            c = get(i) // expecting number, IOOB if there is no number
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d in 0..9) { "Unexpected character '$c' at $i" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $i" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $i" }
            i++
            if (i >= n) break
            c = get(i)
            if (c == ' ') break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m++] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $i" } }
        if (i >= n) break
        i++
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}