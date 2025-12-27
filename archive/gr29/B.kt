fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = IntArray(2 * n)
        for (i in n downTo 1) {
            a[n - i] = i
        }
        a[n] = n
        for (i in 1..n - 1) {
            a[i + n] = i
        }
        println(a.joinToString(" "))
    }
}