fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.sorted()
        var ans = a.sumOf { it.toLong() }
        var target = 0
        for (x in a) {
            if (x > target) target++
            ans -= target
        }
        println(ans)
    }
}