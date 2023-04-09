fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = IntArray(n)
        val b = IntArray(n)
        for (i in 0 until n step 2) {
            a[i] = 2 * n - 1 - i
        }
        for (i in 0 until n step 2) {
            a[n - 1 - i] = 1 + i
        }
        b[n - 1] = 2 * n
        for (i in 0 until n - 1) {
            b[i] = a[i + 1] + 1
        }
        println(a.joinToString(" "))
        println(b.joinToString(" "))
    }
}