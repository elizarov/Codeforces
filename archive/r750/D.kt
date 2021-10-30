import kotlin.math.*

fun main() {
    val MAX = 10000
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        val b = IntArray(n)
        var sum = 0
        val a0 = a[0]
        for (i in 1 until n) {
            val s1 = sum + a[i]
            val s2 = sum - a[i]
            if (s1.absoluteValue < s2.absoluteValue) {
                b[i] = a0
                sum = s1
            } else {
                b[i] = -a0
                sum = s2
            }
        }
        b[0] = -sum
        if (sum == 0) {
            for (i in 1 until n) b[i] = b[i].sign
            b[0] = a[1]
            b[1] -= a[0]
        }
        println(b.joinToString(" "))
    }
}