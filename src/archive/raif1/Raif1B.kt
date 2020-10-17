package archive.raif1

fun main() = System.`in`.bufferedReader().run {
    val a = Array(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val s = readLine()!!
        var l1 = true
        var l2 = true
        for (c in s) when(c) {
            '<' -> l1 = false
            '>' -> l2 = false
        }
        if (l1 || l2) {
            n
        } else {
            var mc = 0
            for (i in 0 until n) {
                if (s[i] == '-' || s[(i + 1) % n] == '-') mc++
            }
            mc
        }
    }
    println(a.joinToString("\n"))
}