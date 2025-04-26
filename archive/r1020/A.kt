fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val g = a.groupingBy { it }.eachCount()
        var res = false
        sol@for ((t, c) in g.toList()) {
            if (c >= 4) { res = true; break }
            if (c >= 2) {
                var p = t + 1
                while (true) {
                    val d = g[p] ?: 0
                    if (d >= 2) { res = true; break@sol }
                    if (d == 0) break
                    p++
                }
            }
        }
        println(if (res) "Yes" else "No")
    }
}