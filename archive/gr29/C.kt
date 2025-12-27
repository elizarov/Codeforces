fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val s = readln()
        var ok = true
        val busy = BooleanArray(n)
        for (i in 1..<n - 1) {
            if (s[i] == '0' && s[i - 1] == '1' && s[i + 1] == '1') {
                if (i - 2 >= 0 && s[i - 2] == '0' && !busy[i - 2]) {
                    busy[i - 2] = true
                } else if (i + 2 < n && s[i + 2] == '0' && !busy[i + 2]) {
                    busy[i + 2] = true
                } else {
                    ok = false
                    break
                }
            }
        }
        println(if (ok) "YES" else "NO")
    }
}