fun main() {
    repeat(readln().toInt()) {
        val (n, x) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }
        var l = Int.MAX_VALUE
        var r = Int.MIN_VALUE
        var c = 0
        for (i in n - 1 downTo 0) {
            val ai = a[i]
            val l1 = minOf(l, ai)
            val r1 = maxOf(r, ai)
            if (r1 - l1 > 2 * x) {
                c++
                l = ai
                r = ai
            } else {
                l = l1
                r = r1
            }
        }
        println(c)
    }
}