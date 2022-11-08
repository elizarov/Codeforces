fun main() {
    repeat(readln().toInt()) {
        val (l, r) = readln().split(" ").map { it.toLong() }
        val ls = sqrt(l)
        val rs = sqrt(r)
        var cnt = 0L
        for (i in 0..2) if (ls * ls + i * ls in l..r) cnt++
        if (rs > ls) {
            for (i in 0..2) if (rs * rs + i * rs in l..r) cnt++
            cnt += (rs - ls - 1) * 3
        }
        println(cnt)
    }
}

fun sqrt(x: Long): Long {
    var t = kotlin.math.sqrt(x.toDouble()).toLong()
    while (t > 1 && t * t > x) t--
    while ((t + 1) * (t + 1) <= x) t++
    return t
}

