fun main() {
    repeat(readln().toInt()) {
        val (l, r) = readln().split(" ").map { it.toLong() }
        println(solveC(l, r))

    }
}

private fun luck(x: Long): Int {
    var r = x
    var min = 10
    var max = 0
    while (r != 0L) {
        val d = (r % 10).toInt()
        r /= 10
        min = minOf(d, min)
        max = maxOf(d, max)
    }
    return max - min
}

fun solveC(l: Long, r: Long): Long {
    if (r < 10) return r
    var c = 1
    var p = 10L
    var ans = 0L
    var best = 10
    fun check(x: Long) {
        val cur = luck(x)
        if (cur < best) {
            best = cur
            ans = x
        }
    }
    while (c <= 18 && p <= r) {
        val l0 = l / p * p
        val r0 = r / p * p
        for (d in 0..9) {
            val x0 = (p - 1) / 9 * d
            for (i in 0..9) {
                val x = l0 + i * p + x0
                if (x in l..r) check(x)
                val y =  r0 - i * p + x0
                if (y in l..r) check(y)
            }
        }
        p *= 10
        c++
    }
    return ans
}
