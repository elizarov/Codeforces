fun main() {
    val a = List(readln().toInt()) {
        val n = readln().toInt()
        val s = readln()
        val m = 2 * n
        var c = 0
        val res = ArrayList<String>()
        var last = -1
        fun flush(j: Int) {
            if (last == -1) return
            res += "${last + 1} ${j + 1}"
            last = -1
        }
        for (i in 0 until m) {
            when(s[i]) {
                '(' -> c++
                ')' -> c--
            }
            if (c < 0 && last == -1) last = i
            if (c > 0) flush(i - 1)
        }
        check(c == 0)
        flush(m - 1)
        if (res.isEmpty()) "0" else "${res.size}\n${res.joinToString("\n")}"
    }
    println(a.joinToString("\n"))
}