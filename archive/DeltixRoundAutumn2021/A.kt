fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toLong() }.toLongArray()
        var cnt = 0
        for (i in 0 until n) {
            while (a[i] % 2 == 0L) {
                a[i] = a[i] / 2
                cnt++
            }
        }
        a.sort()
        a[n - 1] *= 1L shl cnt
        println(a.sum())
    }
}