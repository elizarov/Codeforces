fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val c1 = a.count { it == 1 } / 2
        val c2 = n - 2 * c1
        println(c1 + c2)
    }
}