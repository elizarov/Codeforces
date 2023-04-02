fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val b = a.foldRight(0) { x, y -> x xor y }
        when {
            b == 0 -> println(0)
            n % 2 == 0 -> println(-1)
            else -> println(b)
        }
    }
}