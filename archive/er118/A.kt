fun main() {
    repeat(readLine()!!.toInt()) {
        var (x1, p1) = readLine()!!.split(" ").map { it.toLong() }
        var (x2, p2) = readLine()!!.split(" ").map { it.toLong() }
        while (x1 % 10 == 0L) { x1 /= 10; p1++ }
        while (x2 % 10 == 0L) { x2 /= 10; p2++ }
        val m = minOf(p1, p2)
        p1 -= m
        p2 -= m
        when {
            p1 > 6 -> println('>')
            p2 > 6 -> println('<')
            else -> {
                while (p1 > 0) { x1 *= 10; p1-- }
                while (p2 > 0) { x2 *= 10; p2-- }
                when {
                    x1 > x2 -> println('>')
                    x1 < x2 -> println('<')
                    else -> println('=')
                }
            }
        }
    }
}