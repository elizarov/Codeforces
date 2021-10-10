package archive.raif1

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.split(" ").map { it.toInt() }.sortedDescending()
    println(solveE(n, k, a))
}

fun solveE(n: Int, k: Int, a: List<Int>): Long {
    var l = 1
    var r = a[0] + 1
    fun split(i: Int, t: Int): Int =
        if (t == 1) a[i] else (a[i] - 2) / (t - 1) + 1
    while (l < r - 1) {
        val t = (l + r) / 2
        var cnt = n
        for (i in 0 until n) {
            if (a[i] <= t) break
            val c = split(i, t)
            cnt += c - 1
            if (cnt >= k) break
        }
        if (cnt >= k) {
            l = t
        } else {
            r = t
        }
    }
    var sum = 0L
    var cnt = n
    for (i in 0 until n) {
        if (a[i] <= l) {
            sum += sq(a[i])
        } else {
            val c = minOf(split(i, l), k - cnt + 1)
            cnt += c - 1
            val d = a[i] / c
            val e = a[i] % c
            for (j in 0 until c) {
                sum += sq(if (j < e) d + 1 else d)
            }
        }
    }
    return sum
}

fun sq(x: Int) = x.toLong() * x