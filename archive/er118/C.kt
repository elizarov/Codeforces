fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, h) = readLine()!!.split(" ").map { it.toLong() }
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        var l = 0L
        var r = h
        while (l < r - 1) {
            val m = (l + r) / 2
            var sum = 0L
            var last = -m
            for (t in a) {
                if (last + m - 1 >= t) {
                    sum -= last + m - t
                }
                sum += m
                last = t.toLong()
                if (sum >= h) break
            }
            if (sum >= h) {
                r = m
            } else {
                l = m
            }
        }
        println(r)

    }
}