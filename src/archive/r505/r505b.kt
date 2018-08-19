package archive.r505

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = LongArray(n)
    val b = LongArray(n)
    repeat(n) { i ->
        val (ai, bi) = readLine()!!.split(" ").map { it.toLong() }
        a[i] = ai
        b[i] = bi
    }
    var g = a.zip(b) { i, j -> i * j }.reduce(::gcd)
    for (i in 0 until n) {
        val u = gcd(g, a[i])
        if (u > 1) {
            g = u
        } else {
            g = gcd(g, b[i])
        }
    }
    println(if (g > 1) g else -1)
}

private tailrec fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}
