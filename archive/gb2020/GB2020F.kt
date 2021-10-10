fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val cp = IntArray(m) { -1 }
    val cd = IntArray(m)
    val cs = IntArray(m) { 1 }
    fun cInit(u: Int): Int {
        if (cp[u] < 0) {
            cp[u] = u
            return u
        }
        var c = cp[u]
        while (c != cp[u]) c = cp[u]
        return c
    }
    val cf = IntArray(m)
    val au = BooleanArray(n + 1)
    for (index in 1..n) {
        val x = readLine()!!.split(" ").map { it.toInt() - 1 }
        when (x[0]) {
            0 -> {
                val c = cInit(x[1])
                if (cf[c] == 0) {
                    cf[c] = index
                    au[index] = true
                }
            }
            1 -> {
                val c1 = cInit(x[1])
                val c2 = cInit(x[2])
                if (c1 != c2 && (cf[c1] == 0 || cf[c2] == 0)) {
                    au[index] = true
                    if (cd[c1] < cd[c2]) {
                        cp[c1] = c2
                        cs[c2] += cs[c1]
                        if (cf[c2] == 0) cf[c2] = cf[c1]
                    } else {
                        cp[c2] = c1
                        cs[c1] += cs[c2]
                        if (cf[c1] == 0) cf[c1] = cf[c2]
                        if (cd[c1] == cd[c2]) cd[c1]++
                    }
                }
            }
            else -> error("!!!")
        }
    }
    var ans = 1
    for (i in 0 until m) if (cp[i] == i) {
        if (cf[i] != 0) {
            ans = mul2(ans)
        }
        repeat(cs[i] - 1) { ans = mul2(ans) }
    }
    val auf = ArrayList<Int>()
    for ((i, f) in au.withIndex()) if (f) auf += i
    println("$ans ${auf.size}")
    println(auf.joinToString(" "))
}

private val mod = 1_000_000_007

private fun mul2(x: Int): Int = (2 * x) % mod

/*
5 4
1 1
1 4
2 1 2
2 3 4
2 2 3
 */