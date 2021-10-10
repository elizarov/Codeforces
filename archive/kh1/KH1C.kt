package archive.kh1

fun main() {
    val k = readLine()!!.toInt()
    repeat(k) {
        val s = readLine()!!
        val t = readLine()!!
        println(if (solve(s, t)) "YES" else "NO")
    }
}

private fun solve(s: String, t: String): Boolean {
    var i = 0
    var j = 0
    while (i < s.length && j < t.length) {
        when (s[i]) {
            t[j] -> {}
            '+' -> return false
            else -> { // '-' -> '+'
                i++
                if (i >= s.length || s[i] != '-') return false
            }
        }
        i++
        j++
    }
    return i == s.length && j == t.length
}