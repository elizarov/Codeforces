fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val s = readln()
        val d = ArrayList<Int>()
        for (i in 0 until n / 2 ) {
            if (s[i] != s[n - i - 1]) d += i
        }
        val ans = when {
            d.isEmpty() -> true
            else -> d.last() - d.first() + 1 == d.size
        }
        println(if (ans) "Yes" else "No")
    }
}