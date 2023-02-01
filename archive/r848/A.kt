fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val sum = a.sum()
        var max = Int.MIN_VALUE
        for (i in 0 until n - 1) {
            val si = a[i] + a[i + 1]
            max = maxOf(max, sum - 2 * si)
        }
        println(max)
    }
}