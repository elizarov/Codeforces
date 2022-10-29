fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val b = IntArray(n) { 1 }
        var j = 0
        val ds = a.zipWithNext { a, b -> b - a }.withIndex().filter { it.value < 0 }.sortedByDescending { it.value }
        for ((i, d) in ds) {
            while (d + j + 1 <= 0) j++
            b[j] = i + 2
            j++
        }
        println(b.joinToString(" "))
    }
}