fun main() {
    val n = readln().toInt()
    val p = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
    val q = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
    val pb = inv(p)
    val qb = inv(q)
    var l = minOf(pb[0], qb[0])
    var r = maxOf(pb[0], qb[0])
    var ans = cnt(0, l - 1) + cnt(l + 1, r - 1) + cnt(r + 1, n - 1)
    for (k in 1 until n) {
        val l0 = minOf(pb[k], qb[k])
        val r0 = maxOf(pb[k], qb[k])
        if (r0 < l) ans += (l - r0).toLong() * (n - r)
        if (l0 > r) ans += (l0 - r).toLong() * (l + 1)
        val l1 = minOf(l, l0)
        val r1 = maxOf(r, r0)
        ans += (l - l1).toLong() * (r1 - r)
        l = l1
        r = r1
    }
    ans += (l + 1).toLong() * (n - r)
    println(ans)
}

fun cnt(l: Int, r: Int): Long {
    val k = r - l + 1
    return if (k <= 0) 0L else k.toLong() * (k + 1) / 2
}

fun inv(p: IntArray) = IntArray(p.size).apply {
    for (i in p.indices) this[p[i]] = i
}
