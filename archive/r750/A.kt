fun main() {
    repeat(readLine()!!.toInt()) {
        val (a, b, c) = readLine()!!.split(" ").map { it.toLong() }
        val ss = 1 * a + 2 * b + 3 * c
        var d = ss % 2
        while (true) {
            var s = (ss - d) / 2
            s -= 3 * minOf(s / 3, c)
            s -= 2 * minOf(s / 2, b)
            if (s <= a) break
            d += 2
        }
        println(d)
    }
}