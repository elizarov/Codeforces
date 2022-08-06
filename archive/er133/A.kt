fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = when (n % 3) {
            0 -> n / 3
            1 -> if (n == 1) 2 else ((n - 4) / 3 + 2)
            2 -> 1 + n / 3
            else -> error("!!!")
        }
        println(a)
    }
}