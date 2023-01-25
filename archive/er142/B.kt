fun main() {
    repeat(readln().toInt()) {
        val (a0, a1, a2, a3) = readln().split(" ").map { it.toInt() }
        val sum = a0 + a1 + a2 + a3
        if (a0 == 0) {
            println(sum.coerceAtMost(1))
        } else {
            val b = minOf(a1, a2)
            var r = a1 + a2 - 2 * b + a3
            val c = minOf(a0, r)
            r -= c
            r = r.coerceAtMost(1)
            println(a0 + 2 * b + c + r)
        }
    }
}