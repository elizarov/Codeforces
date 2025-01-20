fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        if (n == 6) {
            println("1 1 2 3 1 2")
        } else {
            println((listOf(1, 1) + (2..n - 2) + 1).joinToString(" "))
        }
    }
}