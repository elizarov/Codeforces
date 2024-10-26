fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        val s = a.sortedBy { maxOf(it[0], it[1])  }.sortedBy { minOf(it[0], it[1]) }.joinToString(" ") { "${it[0]} ${it[1]}"}
        println(s)
    }
}