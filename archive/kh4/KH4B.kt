package archive.kh4

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k1, k2) = readLine()!!.split(" ").map { it.toInt() }
        val s = readLine()!!
        var m = 0
        var prev = 0
        for (c in s) {
            if (c == '1') {
                prev = minOf(k2 - prev, k1)
                m += prev
            } else {
                prev = 0
            }
        }
        println(m)
    }
}