package algo

/*
    Manacher's algorithm.

    Returns:
      d1 - number of odd polyndromes centered at i
      d2 - number of even polyndromes centered at [i - 1, i]
*/

fun polyndromes(s: String): Pair<IntArray, IntArray> {
    val n = s.length
    val d1 = IntArray(n)
    var l = 0
    var r = -1
    for (i in 0 until n) {
        var k = if (i > r) 1 else minOf(d1[l +  r - i], r - i + 1)
        while (i + k < n && i - k >= 0 && s[i + k] == s[i - k]) k++
        d1[i] = k
        if (i + k - 1 > r) {
            l = i - k + 1
            r = i + k - 1
        }
    }
    l = 0
    r = -1
    val d2 = IntArray(n)
    for (i in 0 until n) {
        var k = if (i > r) 0 else minOf(d2[l +  r - i + 1], r - i + 1)
        while (i + k < n && i - k - 1>= 0 && s[i + k] == s[i - k - 1]) k++
        d2[i] = k
        if (i + k - 1 > r) {
            l = i - k
            r = i + k - 1
        }
    }
    return d1 to d2
}

fun main() {
    val (d1, d2) = polyndromes("abacxxcc")
    check(d1.contentEquals(intArrayOf(1, 2, 1, 1, 1, 1, 1, 1)))
    check(d2.contentEquals(intArrayOf(0, 0, 0, 0, 0, 2, 0, 1)))
}