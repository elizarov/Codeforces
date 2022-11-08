fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        var ans = 0L
        var max = 0
        repeat(n) {
            val (a, b) = readln().split(" ").map { it.toInt() }.sorted()
            ans += 2 * a
            max = maxOf(max, b)
        }
        ans += 2 * max
        println(ans)
    }
}