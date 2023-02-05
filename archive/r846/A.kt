fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val b = a.withIndex().filter { it.value % 2 != 0 }
        val c = a.withIndex().filter { it.value % 2 == 0 }
        when {
            b.isEmpty() -> {
                println("NO")
            }
            b.size >= 3 -> {
                println("YES")
                println("${b[0].index + 1} ${b[1].index + 1} ${b[2].index + 1}")
            }
            c.size >= 2 -> {
                println("YES")
                println("${b[0].index + 1} ${c[0].index + 1} ${c[1].index + 1}")
            }
            else -> {
                println("NO")
            }
        }
    }
}