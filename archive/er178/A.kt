fun main() {
    repeat(readln().toInt()) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        val x = (a + b + c) / 3
        val ok = (a + b + c) % 3 == 0 && a <= x && b <= x && c >= x
        println(if (ok) "YES" else "NO")
    }
}