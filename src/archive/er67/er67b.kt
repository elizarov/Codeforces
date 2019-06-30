package archive.er67

fun main() {
    val n = readLine()!!.toInt()
    val s = readLine()!!
    val m = readLine()!!.toInt()
    val t = List(m) { readLine()!! }
    val result = solveFriends(n, s, t)
    println(result.joinToString("\n"))
}

fun solveFriends(n: Int, s: String, t: List<String>): List<Int> {
    val k = IntArray(n * 26)
    k[s[0] - 'a'] = 1
    for (i in 1 until n) {
        val ofs = i * 26
        k.copyInto(k, ofs, ofs - 26, ofs)
        k[ofs + (s[i] - 'a')]++
    }
    val c = IntArray(26)
    val result = ArrayList<Int>(t.size)
    for (q in t) {
        c.fill(0)
        for (char in q) c[char - 'a']++
        var l = -1
        var r = n
        while (l + 1 < r) {
            val m = (l + r) / 2
            val ofs = m * 26
            var ok = true
            for (j in 0 until 26) {
                if (k[ofs + j] < c[j]) {
                    ok = false
                    break
                }
            }
            if (ok) {
                r = m
            } else {
                l = m
            }
        }
        result.add(r + 1)
    }
    return result
}

