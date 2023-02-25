fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().map { it.digitToInt() }.toIntArray()
        val b = readln().map { it.digitToInt() }.toIntArray()
        val res = solveD(n, a, b)
        if (res == null) {
            println(-1)
        } else {
            println(res.size)
            if (res.isNotEmpty()) println(res.joinToString(" "))
        }
    }
}

fun solveD(n: Int, a: IntArray, b: IntArray): List<Int>? {
    val res = ArrayList<Int>()
    fun apply(k: Int) {
        res += k
        when {
            k > 0 -> for (i in 0 until n - k) a[i] = a[i] xor a[i + k]
            k < 0 -> for (i in n - 1 downTo -k) a[i] = a[i] xor a[i + k]
            else -> error("!!!")
        }
    }
    var i = a.indexOf(1)
    val j = b.lastIndexOf(1)
    if (i < 0) return if (j < 0) res else null
    if (j < 0) return null
    if (i > j) {
        apply(i - j)
        i = j
    }
    for (k in j + 1 until n) {
        if (a[k] != 0) apply(i - k)
    }
    i = a.lastIndexOf(1)
    check(i <= j)
    if (i < j) apply(i - j)
    for (k in j - 1 downTo 0) {
        if (a[k] != b[k]) apply(j - k)
    }
    return res
}

