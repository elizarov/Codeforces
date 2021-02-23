package archive.r704

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        val m = IntArray(n)
        var max = -1
        var maxI = -1
        for (i in 0 until n) {
            if (a[i] > max) {
                max = a[i]
                maxI = i
            }
            m[i] = maxI
        }
        val b = IntArray(n)
        var i = n - 1
        var k = 0
        while (i >= 0) {
            val j = m[i]
            a.copyInto(b, k, j, i + 1)
            k += i + 1 - j
            i = j - 1
        }
        println(b.joinToString(" "))
    }
}