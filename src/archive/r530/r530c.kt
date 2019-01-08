package archive.r530

import kotlin.math.*

fun main() {
    val inp = readLine()!!.split(" ")
    val n = inp[0].toInt()
    val s = inp[1].toLong()
    val p = constructTree(n, s)
    if (p == null) {
        println("No")
    } else {
        println("Yes")
        println(p.asSequence().drop(2).joinToString(" "))
    }
}

fun constructTree(n: Int, s: Long): IntArray? {
    val maxSum = maxSum(n)
    if (maxSum < s) return null
    if (maxSum == s) return construct(n, s, 1)
    if (minSum(n, n - 1) > s) return null
    // bs
    return null
}

fun construct(n: Int, s: Long, bf: Int): IntArray? {
    val p = IntArray(n + 1)
    return p
}

fun minSum(n: Int, bf: Int): Long {
    var sum = 0L
    var r = n
    var w = 1L
    var h = 1L
    while (r > 0) {
        val c = min(w, r.toLong()).toInt()
        sum += c * h
        h++
        w *= bf
        h++
        r -= c
    }
    return sum
}

fun maxSum(n: Int): Long {
    var sum = 0L
    var r = n
    var h = 1L
    while (r > 0) {
        sum += h
        h++
        r--
    }
    return sum
}