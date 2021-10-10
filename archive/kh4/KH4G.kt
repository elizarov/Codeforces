package archive.kh4

fun main() {
    val (k, n) = readLine()!!.split(" ").map { it.toInt() }
    val p = readLine()!!.split(" ").map { it.toInt() }
    val x = readLine()!!.split(" ").map { it.toInt() }
    val c = solveCams(n, p, x)
    if (c == null) {
        println("NO")
    } else {
        println("YES")
        println("${c.s1} ${c.p1}")
        println("${c.s2} ${c.p2}")
    }
}

private class Cams(val s1: Int, val p1: Int, val s2: Int, val p2: Int)

private tailrec fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)

private fun solveCams(n: Int, p: List<Int>, x: List<Int>): Cams? {
    fun place2(s1: Int, p1: Int): Cams? {
        val f = BooleanArray(n) { j -> (x[j] - s1) % p1 == 0 }
        val i = f.indexOf(false)
        if (i < 0) return Cams(s1, p1, s1, p1)
        val s2 = x[i]
        var j = i + 1
        while (j < n && f[j]) j++
        if (j >= n) return Cams(s1, p1, s2, p[0])
        var p2m = x[j] - s2
        while (++j < n) {
            if (!f[j]) p2m = gcd(p2m, x[j] - s2)
        }
        for (p2 in p) if (p2m % p2 == 0) {
            return Cams(s1, p1, s2, p2)
        }
        return null
    }
    fun tryPlace2(s1: Int, p1m: Int): Cams? {
        for (p1 in p) if (p1m % p1 == 0) {
            place2(s1, p1)?.let { return it }
        }
        return null
    }
    if (n == 2) return Cams(x[0], p[0], x[1], p[0])
    for (t1 in 1..2) {
        tryPlace2(x[0], x[t1] - x[0])?.let { return it }
    }
    return tryPlace2(x[1], x[2] - x[1])
}
