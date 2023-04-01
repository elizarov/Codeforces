fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val ok = a.withIndex().any { it.value <= it.index + 1 }
        println(if (ok) "YES" else "NO")
    }
}