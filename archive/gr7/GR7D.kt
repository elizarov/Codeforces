package archive.gr7

fun main() {
    val br = System.`in`.bufferedReader()
    val ans = Array(br.readLine()!!.toInt()) {
        solve(br.readLine())
    }
    println(ans.joinToString("\n"))
}

private fun solve(s: String): String {
    var i = 0
    var j = s.lastIndex
    while (i < j - 1 && s[i] == s[j]) {
        i++
        j--
    }
    if (i >= j) return s
    val p = polyMax(s.substring(i, j + 1))
    return s.substring(0, i) + p + s.substring(j + 1, s.length)
}

private fun polyMax(s: String): String {
    val n = s.length
    val d = IntArray(n)
    var l = 0
    var r = -1
    var maxL = 0
    var maxR = 0
    for (i in 0 until n) {
        var k = if (i > r) 1 else minOf(d[l +  r - i], r - i + 1)
        while (i + k < n && i - k >= 0 && s[i + k] == s[i - k]) k++
        d[i] = k
        if (i + k - 1 > r) {
            l = i - k + 1
            r = i + k - 1
        }
        if (k == i + 1) maxL = maxOf(maxL, 2 * k - 1)
        if (i + k == n) maxR = maxOf(maxR, 2 * k - 1)
    }
    l = 0
    r = -1
    for (i in 0 until n) {
        var k = if (i > r) 0 else minOf(d[l +  r - i + 1], r - i + 1)
        while (i + k < n && i - k - 1>= 0 && s[i + k] == s[i - k - 1]) k++
        d[i] = k
        if (i + k - 1 > r) {
            l = i - k
            r = i + k - 1
        }
        if (k == i) maxL = maxOf(maxL, 2 * k)
        if (i + k == n) maxR = maxOf(maxR, 2 * k)
    }
    return if (maxL >= maxR) s.substring(0, maxL) else s.substring(n - maxR, n)
}