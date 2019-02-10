package archive.er58

fun main() {
    val s = readLine()!!
    println(solveBayan(s))
}

fun solveBayan(s: String): Int {
    val n = s.length
    var i = 0
    while (i < n && s[i] != '[') i++
    if (i >= n) return -1
    while (i < n && s[i] != ':') i++
    if (i >= n) return -1
    var j = n - 1
    while (j >= 0 && s[j] != ']') j--
    if (j <= i) return -1
    while (j >= 0 && s[j] != ':') j--
    if (j <= i) return -1
    var cnt = 4
    for (k in i..j) if (s[k] == '|') cnt++
    return cnt
}
