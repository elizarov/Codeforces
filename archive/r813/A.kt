fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val p = readln().split(" ").map { it.toInt() }
        println(p.subList(0, k).count { it > k })
    }
}