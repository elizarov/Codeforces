fun main() {
    val n = readLine()!!.toInt()
    val a = Array(n) { readLine()!!.analyzeS() }
    val dp = IntArray(1 shl n) { -1 }
    val sumB = a.sumOf { it.b }
    for (i in 0 until n) {
        dp[1 shl i] = a[i].sc[sumB - a[i].b] ?: 0
    }
    fun find(um: Int, b0: Int): Int {
        dp[um].let { if (it >= 0) return it }
        var res = 0
        for (i in 0 until n) if ((1 shl i) and um != 0) {
            val b1 = b0 + a[i].b
            val s1 = a[i].sc[b0] ?: 0
            res = maxOf(res, s1 + if (b0 + a[i].m >= 0) find(um and (1 shl i).inv(), b1) else 0)
        }
        dp[um] = res
        return res
    }
    val ans = find((1 shl n) - 1, 0)
    println(ans)
}

class SS(val b: Int, val m: Int, val sc: Map<Int,Int>)

fun String.analyzeS(): SS {
    var b = 0
    var m = 0
    val sc = HashMap<Int,Int>()
    for (c in this) {
        when(c) {
            '(' -> {
                b++
            }
            ')' -> {
                b--
                m = minOf(b, m)
                if (b <= 0 && b == m) {
                    sc[-b] = (sc[-b] ?: 0) + 1
                }
            }
        }
    }
    return SS(b, m, sc)
}