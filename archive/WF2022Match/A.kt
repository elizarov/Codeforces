fun main() {
    repeat(readln().toInt()) {
        val a = readln().split(" ").map { it.toInt() }
        val s = a.sortedDescending()
        val b = a.map { x ->
            if (x == s[0] && x != s[1]) 0 else s[0] + 1 - x 
        }
        println(b.joinToString(" "))
    }
}