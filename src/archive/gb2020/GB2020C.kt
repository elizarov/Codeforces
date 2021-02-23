fun main() {
    val ans = IntArray(readLine()!!.toInt()) {
        val s = readLine()!!.toCharArray()
        var c = 0
        for (i in 1 until s.size) {
            if (s[i] == s[i - 1] || i > 1 && s[i] == s[i - 2]) {
                c++
                s[i] = '*'
            }
        }
        c
    }
    println(ans.joinToString("\n"))
}