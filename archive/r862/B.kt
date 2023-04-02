fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val s = readln()
        val ch = s.min()
        val rd = IntArray(n)
        rd[n - 1] = -1
        for (i in n - 2 downTo 0) {
            rd[i] = when {
                s[i + 1] < s[i] -> -1
                s[i + 1] > s[i] -> 1
                else -> rd[i + 1]
            }
        }
        var j = s.lastIndexOf(ch)
        for (i in 0 until n) if (s[i] == ch && rd[i] < 0) {
            j = i
            break
        }
        if (j == 0) {
            println(s)
        } else {
            println(ch + s.substring(0, j) + s.substring(j + 1, n))
        }
    }
}