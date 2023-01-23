fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        println(a.zipWithNext().count { (a, b) -> a.mod(2) == b.mod(2) })
    }
}