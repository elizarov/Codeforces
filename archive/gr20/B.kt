fun main() {
    repeat(readLine()!!.toInt()) {
        val s = readln()
        var a = 0
        var ok = true
        for (c in s) {
            when(c) {
                'A' -> a++
                'B' -> {
                    a--
                    if (a < 0) ok = false
                }
            }
        }
        if (s.last() == 'A') ok = false
        println(if (ok) "YES" else "NO")
    }
}