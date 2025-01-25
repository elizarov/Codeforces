fun main() {
    repeat(readln().toInt()) {
        val (l, r) = readln().split(" ").map { it.toInt() }
        println(if (l == 1 && r == 1) 1 else r - l)
    }
}