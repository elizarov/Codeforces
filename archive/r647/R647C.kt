package archive.r647

fun main() = System.`in`.bufferedReader().run {
    val n = readLine()!!.toInt()
    val ps = IntArray(2 * n)
    repeat(n) { i ->
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        ps[2 * i] = a
        ps[2 * i + 1] = b
    }
    val m = 1 shl 20
    val gf = IntArray(m)
    val gc = IntArray(m)
    val ge = IntArray(m)
    val gn = IntArray(2 * n)
    val s = IntArray(2 * n)
    val r = IntArray(2 * n)
    val v = BooleanArray(n)
    find@for (b in 20 downTo 0) {
        val mask = (1 shl b) - 1
        gf.fill(-1, 0, mask + 1)
        gc.fill(0, 0, mask + 1)
        ge.fill(0, 0, mask + 1)
        for ((i, p) in ps.withIndex()) {
            val u = p and mask
            gc[u]++
            gn[i] = gf[u]
            gf[u] = i
            val v = ps[i xor 1] and mask
            if (u != v) ge[u]++
        }
        for (j in 0..mask) {
            if (gc[j] % 2 != 0) continue@find
            if (ge[j] % 2 != 0) continue@find
        }
        v.fill(false)
        var rp = 0
        var sp = 2
        s[0] = 0
        s[1] = 1
        v[0] = true
        while (sp > 0) {
            val i = s[sp - 1]
            val u = ps[i] and mask
            val j = gf[u]
            if (j >= 0) {
                if (!v[j shr 1]) {
                    s[sp++] = j
                    s[sp++] = j xor 1
                    v[j shr 1] = true
                }
                gf[u] = gn[j]
            } else {
                r[rp++] = s[--sp] + 1
                r[rp++] = s[--sp] + 1
            }
        }
        if (rp == 2 * n) {
            println(b)
            println(r.joinToString(" "))
            return
        }
    }
}