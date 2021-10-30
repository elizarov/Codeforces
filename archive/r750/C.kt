fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val s = readLine()!!
        var ans = -1
        find@for (rc in s.toList().distinct()) {
            var i = 0
            var j = n - 1
            var rem = 0
            while (i < j) {
                if (s[i] == s[j]) {
                    i++
                    j--
                    continue
                }
                if (s[i] == rc) {
                    i++
                    rem++
                    continue
                }
                if (s[j] == rc) {
                    j--
                    rem++
                    continue
                }
                continue@find
            }
            if (ans < 0 || rem < ans) ans = rem
        }
        println(ans)
    }
}