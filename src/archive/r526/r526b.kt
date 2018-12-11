package archive.r526

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!
    val t = readLine()!!
    println(computeStrings(n, s, t, k))
}

private fun computeStrings(n: Int, s: String, t: String, k: Int): Long {
    val kl = k.toLong()
    var c = 0L
    var nd = 0L
    var i = 0
    while (i < n && s[i] == t[i]) {
        c++
        i++
    }
    if (i >= n) return c
    c += min(k, 2)
    i++
    while (i < n) {
        if (s[i] == 'a') {
            nd++
        }
        if (t[i] == 'b') {
            nd++
        }
        c += min(kl, 2 + nd)
        nd = min(kl, 2 * nd)
        i++
    }
    return c
}
