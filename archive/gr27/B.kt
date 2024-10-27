fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val ans = when {
            n == 1 || n == 3 -> "-1"
            n % 2 == 0 -> "3".repeat(n - 2) + "66"
            else -> "3".repeat(n - 4) + "6366"
        }
        println(ans)
    }
}