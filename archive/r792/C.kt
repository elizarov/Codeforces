fun main() {
    data class SP(val j: Int, val k: Int)
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val a = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
        var ok = true
        var fix: SP? = null
        val sr = ArrayList<Int>()
        fun fix(j: Int, k: Int): Boolean {
            fix?.let { fix ->
                if (fix.j != j || fix.k != k) {
                    ok = false
                    return false
                }
                return true
            }
            fix = SP(j, k)
            return true
        }
        for (i in 0 until n) {
            val dl = ArrayList<Int>()
            for (j in 0 until m - 1) {
                if (a[i][j] > a[i][j + 1]) dl += j
            }
            when (dl.size) {
                0 -> sr += i
                1 -> {
                    val j = dl[0]
                    val a0 = a[i].getOrNull(j - 1) ?: Int.MIN_VALUE
                    val a1 = a[i][j]
                    val a2 = a[i][j + 1]
                    val a3 = a[i].getOrNull(j + 2) ?: Int.MAX_VALUE
                    // a0 ? a2 < a1 ? a3
                    if (a0 > a2 || a1 > a3) {
                        ok = false
                        break
                    }
                    if (!fix(j, j + 1)) break
                }
                2 -> {
                    val j = dl[0]
                    val k = dl[1] + 1
                    val a0 = a[i].getOrNull(j - 1) ?: Int.MIN_VALUE
                    val a1 = a[i][j]
                    val a2 = a[i][j + 1]
                    val a3 = a[i][k - 1]
                    val a4 = a[i][k]
                    val a5 = a[i].getOrNull(k + 1) ?: Int.MAX_VALUE
                    // a0 ? a4 ? a2 .... a3 ? a1 ? a5
                    if (a0 > a4 || a4 > a2 || a3 > a1 || a1 > a5) {
                        ok = false
                        break
                    }
                    if (!fix(j, k)) break
                }
                else -> {
                    ok = false
                    break
                }
            }
        }
        if (ok) {
            fix?.let { fix ->
                for (i in sr) {
                    if (a[i][fix.j] != a[i][fix.k]) ok = false
                }
            }
        }
        if (ok) {
            val fix = fix
            if (fix == null) {
                println("1 1")
            } else {
                println("${fix.j + 1} ${fix.k + 1}")
            }
        } else {
            println(-1)
        }
    }
}