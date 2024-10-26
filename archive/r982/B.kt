import java.util.*

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var best = Int.MAX_VALUE
        for (i in n - 1 downTo 0) {
            var cnt = i
            for (j in i + 1..<n) if (a[j] > a[i]) cnt++
            if (cnt < best) best = cnt
        }
        println(best)
    }
}