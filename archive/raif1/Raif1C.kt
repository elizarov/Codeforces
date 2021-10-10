package archive.raif1

fun main() = System.`in`.bufferedReader().run {
    val a = Array(readLine()!!.toInt()) {
        val s = readLine()!!
        var p0 = 0
        var p1 = 1
        for (i in 0 until s.length) {
            val q = when(s[i]) {
                'A' -> p0 + 1
                'B' -> if (p0 == 0) minOf(1, p1) else minOf(p0 - 1, p1)
                else -> error("!!")
            }
            p1 = p0
            p0 = q
        }
        p0
    }
    println(a.joinToString("\n"))
}