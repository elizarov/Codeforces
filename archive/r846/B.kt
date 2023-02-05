fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        var max = 1L
        var r = a.sumOf { it.toLong() }
        var l = 0L
        for (i in 0 until n - 1) {
            l += a[i]
            r -= a[i]
            max = maxOf(max, gcd(l, r))
        }
        println(max)
    }
}

fun gcd(x: Long, y: Long): Long =
    if (y == 0L) x else gcd(y, x % y)
