fun main() {
    repeat(readln().toInt()) {
        val (n, m1, m2) = readln().split(" ").map { it.toInt() }
        solveE(n, m1, m2)
    }
}

fun solveE(n: Int, m1: Int, m2: Int) {
    val pc = (factor(m1) + factor(m2)).groupingBy { it }.eachCount().toList()
    val k = pc.size
    val n2 = n.toLong() * n
    var cnt = 0
    var xor = 0
    var min = 0
    val cc = IntArray(k)
    val z = Array(k) { i ->
        val (p, c) = pc[i]
        LongArray(c + 1).also { r ->
            r[0] = 1
            for (j in 1..c) r[j] = r[j - 1] * p
        }
    }

    fun solve(cur: Long, rem: Long, i: Int) {
        if (cur > n || rem > n) return
        if (i >= k) {
            min = minOf(min, cur.toInt())
            return
        }
        val (p, _) = pc[i]
        for (j in 0..cc[i]) {
            solve(cur * z[i][j], rem * z[i][cc[i] - j], i + 1)
        }
    }

    fun gen(cur: Long, i: Int) {
        if (cur > n2) return
        if (i >= k) {
            min = n + 1
            solve(1, 1, 0)
            if (min <= n) {
                cnt++
                xor = xor xor min
            }
            return
        }
        val (p, c) = pc[i]
        for (j in 0..c) {
            cc[i] = j
            gen(cur * z[i][j], i + 1)
        }
    }
    gen(1, 0)
    println("$cnt $xor")
}

fun factor(m: Int): List<Int> = buildList {
    var rem = m
    var t = 2
    while (t * t <= rem) {
        while (rem % t == 0) {
            add(t)
            rem /= t
        }
        t++
    }
    if (rem > 1) add(rem)
}