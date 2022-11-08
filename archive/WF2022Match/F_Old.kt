import java.lang.Math.abs

fun main() {
    repeat(readln().toInt()) {
        val (n, a, b) = readln().split(" ").map { it.toInt() }
        val x = readln().padStart(n, '0').reversed()
        val f = Array(n + 1) { Array(n + 1) { Array(a) { BooleanArray(b) } } }
        f[0][0][0][0] = true
        val powa = (1..n).runningFold(1) { acc, _ -> (acc * 10) % a }
        val powb = (1..n).runningFold(1) { acc, _ -> (acc * 10) % b }
        for (i in 1..n) {
            for (na in 0..i) {
                val nb = i - na
                if (na > 0) {
                    val da = x[i - 1].digitToInt() * powa[na - 1]
                    for (ra in 0 until a) for (rb in 0 until b) {
                        val pa = ((ra + da) % a)
                        if (f[i - 1][na - 1][pa][rb]) f[i][na][ra][rb] = true
                    }
                }
                if (nb > 0) {
                   val db = x[i - 1].digitToInt() * powb[nb - 1]
                    for (ra in 0 until a) for (rb in 0 until b) {
                        val pb = ((rb + db) % b)
                        if (f[i - 1][na][ra][pb]) f[i][na][ra][rb] = true
                    }
                }
            }
        }
        var best = Int.MAX_VALUE
        var bna = -1
        for (na in 1..n-1) {
            if (f[n][na][0][0]) {
                val nb = n - na
                val cur = abs(na - nb)
                if (cur < best) {
                    best = cur
                    bna = na
                }
            }
        }
        if (best == Int.MAX_VALUE) {
            println(-1)
        } else {
            val s = CharArray(n)
            var na = bna
            var ra = 0
            var rb = 0
            loop@for (i in n downTo  1) {
                val nb = i - na
                if (na > 0) {
                    val da = x[i - 1].digitToInt() * powa[na - 1]
                    val pa = ((ra + da) % a)
                    if (f[i - 1][na - 1][pa][rb]) {
                        ra = pa
                        na--
                        s[i-1] = 'R'
                        continue@loop
                    }
                }
                if (nb > 0) {
                    val db = x[i - 1].digitToInt() * powb[nb - 1]
                    val pb = ((rb + db) % b)
                    if (f[i - 1][na][ra][pb]) {
                        rb = pb
                        s[i-1] = 'B'
                        continue@loop
                    }
                }
                error("Fail")
            }
            println(s.reversed().joinToString(""))
        }
    }
}