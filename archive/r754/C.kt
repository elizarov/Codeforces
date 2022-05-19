import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val s = readLine()!!
        var ans = Int.MAX_VALUE
        for (i in 0 until n) {
            if (s[i] != 'a') continue
            if (i < n - 1 && s[i + 1] == 'a') ans = min(ans, 2)
            if (i < n - 2 && s[i + 2] == 'a') ans = min(ans, 3)
            if (i < n - 3 && s[i + 3] == 'a' && s[i + 1] != s[i + 2]) ans = min(ans, 4)
            if (i < n - 6 && s[i + 3] == 'a' && s[i + 6] == 'a' && s[i + 1] != s[i + 4] && s[i + 2] != s[i + 5]) ans = min(ans, 7)
        }
        println(if (ans == Int.MAX_VALUE) -1 else ans)
    }
}