package archive.r651

fun main() {
    val n = readLine()!!.toInt()
    val s = readLine()!!
    val t = readLine()!!
    println(solveE(n, s, t))
}

fun solveE(n: Int, s: String, t: String): Int {
    var prev = '.'
    var len = 0
    var c0 = 0
    var c1 = 0
    var l = IntArray(n)
    var lc = 0
    fun flush() {
        if (len > 0) l[lc++] = len
    }
    for (i in 0 until n) {
        if (s[i] == t[i]) continue
        when (s[i]) {
            '0' -> c0++
            '1' -> c1++
            else -> error("!")
        }
        if (s[i] != prev) {
            flush()
            prev = s[i]
            len = 1
        } else {
            len++
        }
    }
    flush()
    if (c0 != c1) return -1
    var k = IntArray(n)
    var ans = 0
    while (lc > 0) {
        for (i in 0 until lc / 2 * 2) l[i]--
        var kc = 0
        var p = -1
        for (i in 0 until lc) {
            if (l[i] == 0) continue
            if (i % 2 == p) {
                k[kc - 1] += l[i]
            } else {
                p = i % 2
                k[kc++] = l[i]
            }
        }
        ans++
        l = k.also { k = l }
        lc = kc
    }
    return ans
}