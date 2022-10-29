fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }
        if (a.all { it == 0}) {
            println("NO")
        } else {
            println("YES")
        }
    }
}