package archive.gr9

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val b = solveD(n, a)
        println(b.size)
        println(b.joinToString(" "))
    }
}

fun solveD(n: Int, a0: List<Int>): List<Int> {
    val a = a0.toIntArray()
    val b = ArrayList<Int>()
    val p = IntArray(n + 1)
    for (i in 0 until n) {
        p[a[i]]++
    }
    fun mex(): Int {
        var t = 0
        while (p[t] > 0) t++
        return t
    }
    fun set(i: Int, x: Int) {
        p[a[i]]--
        a[i] = x
        p[x]++
    }
    var z = n
    repeat(2 * n) {
        val k = mex()
        if (k == z) {
            b.add(z)
            z--
            set(z, k)
            if (z == 0) return b
        } else {
            b.add(k + 1)
            set(k, k)
        }
    }
    return b
}