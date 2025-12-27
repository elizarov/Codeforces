fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val sum = a.sumOf { it.toLong() }
        val g = a.groupingBy { it }.eachCount().toList().sortedByDescending { it.second }
        var parity = 0
        var alice = 0L
        for ((p, cp) in g) {
            if (p % 2 == 0 || parity != 0) {
                alice += (p / 2).toLong() * cp
            } else {
                alice += ((p + 1) / 2).toLong() * cp
            }
            parity = (parity + p) % 2
        }
        println("${alice} ${sum - alice}")
    }
}