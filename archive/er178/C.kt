fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val s = readln()
        val a = s.withIndex().filter { it.value == 'A' }.map { it.index }
        val b = s.withIndex().filter { it.value == 'B' }.map { it.index }
        val alice = "Alice"
        val bob = "Bob"
        val ans = when {
            b.size == 1 -> if (n == 2 && b[0] == 0) bob else alice
            b.last() == n - 1 -> bob
            a.size == 1 -> bob
            a[0] == 0 -> alice
            a[a.size - 2] == n - 2 -> alice
            else -> bob
        }
        println(ans)
        
    }
}