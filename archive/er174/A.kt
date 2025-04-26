fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val b = readln().split(" ").map { it.toInt() }
        var ok = true
        for (i in 1..b.size - 2) if (b[i - 1] == 1 && b[i] == 0 && b[i + 1] == 1) ok = false
        println(if (ok) "YES" else "NO")
    }
}