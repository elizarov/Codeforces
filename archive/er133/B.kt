fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        println(n)
        val a = (1..n).toMutableList()
        println(a.joinToString(" "))
        for (i in 1 until n) {
            val t = a[i]
            a[i] = a[i - 1]
            a[i - 1] = t
            println(a.joinToString(" "))
        }
    }
}