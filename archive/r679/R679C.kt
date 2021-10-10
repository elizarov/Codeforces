package archive.r679

fun main() = System.`in`.bufferedReader().run {
    val ans = LongArray(readLine()!!.toInt()) {
        val (a, b, c, d) = readLine()!!.split(" ").map { it.toLong() }
        if (b * c >= a) {
            if (d >= c) {
                a
            } else {
                if (b * d >= a) {
                    a
                } else {
                    val k = minOf((c - 1) / d, a / (b * d))
                    a * (k + 1) - b * d * (k * (k + 1) / 2)
                }
            }
        } else {
            -1L
        }
    }
    println(ans.joinToString("\n"))
}