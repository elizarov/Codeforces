fun main() {
    repeat(readln().toInt()) {
        val q = readln().toInt()
        var min = 1L
        var max = Long.MAX_VALUE
        val ans = LongArray(q) {
            val s = readln().split(" ").map { it.toInt() }
            when (s[0]) {
                1 -> {
                    val (_, a, b, n) = s
                    var min1 = b + 1 + (n - 1L) * (a - b)
                    if (min1 <= a) min1 = 1
                    val max1 = a + (n - 1L) * (a - b)
                    if (max1 < min || min1 > max) {
                        0
                    } else {
                        min = maxOf(min, min1)
                        max = minOf(max, max1)
                        1
                    }
                }
                2 -> {
                    val (_, a, b) = s
                    val n1 = ((min - b - 1).floorDiv(a - b) + 1).coerceAtLeast(1)
                    val n2 = ((max - b - 1).floorDiv(a - b) + 1).coerceAtLeast(1)
                    if (n1 == n2) n1 else -1
                }
                else -> error("!!!")
            }
        }
        println(ans.joinToString(" "))
    }
}