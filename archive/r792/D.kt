fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }
        val s = a.sumOf { it.toLong() }
        val b = a.mapIndexed { i, v -> v - (n - 1 - i) }.sortedDescending()
        var res = s
        var c = s
        for (i in 0 until k) {
            c -= b[i] + i
            res = minOf(res, c)
        }
        println(res)
    }
}