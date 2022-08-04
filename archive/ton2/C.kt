import java.util.*

fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() - 1 }.sorted()
        val ls = (a.zipWithNext { i, j -> j - i - 1 } +
                (a.first() + n - a.last() - 1)).sortedDescending()
        var step = 0
        var saved = 0
        for (s in ls) {
            val sr = s - 2 * step
            if (sr >= 3) {
                saved += sr - 1
                step += 2
                continue
            }
            if (sr >= 1) {
                saved += 1
                step += 1
                continue
            }
            break
        }
        println(n - saved)
    }
}

/*
1
20 3
3 7 12
--
11
 */