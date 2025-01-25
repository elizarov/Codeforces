fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val s = a.sum()
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        for (p in 0..s / 2) {
            val q = s - 2 * p
            if (q + p > n) continue
            min = minOf(min, q)
            max = maxOf(max, q)
        }
        println("$min $max")
    }
}