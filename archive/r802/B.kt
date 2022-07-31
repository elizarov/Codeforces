fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val s = readln()
        val q = if (s[0] != '9') {
            s.map { '0' + (9 - (it - '0')) }.joinToString("")
        } else {
            val a = ArrayList<Char>()
            var r = 0
            for (c in s.reversed()) {
                r += 1 - (c - '0')
                if (r >= 0) {
                    a += '0' + r
                    r = 0
                } else {
                    a += '0' + (r + 10)
                    r = -1
                }
            }
            a.reversed().joinToString("")
        }
        println(q)
    }
}