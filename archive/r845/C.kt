private const val MAX = 100_000
private val divs = Array(MAX + 1) { ArrayList<Int>() }.also { divs ->
    for (i in 1..MAX) {
        var k = i
        while (k <= MAX) {
            divs[k] += i
            k += i
        }
    }
}

fun main() {
    repeat(readln().toInt()) {
        val (_, m) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }.distinct().sorted()
        var i = 0
        var j = 0
        val c = IntArray(m + 1)
        var tot = 0
        var ans = Int.MAX_VALUE
        while (i < a.size) {
            while (j < a.size && tot < m) {
                for (t in divs[a[j]]) {
                    if (t > m) break
                    if (c[t]++ == 0) tot++
                }
                j++
            }
            if (tot == m) {
                ans = minOf(ans, a[j - 1] - a[i])
            }
            for (t in divs[a[i]]) {
                if (t > m) break
                if (--c[t] == 0) tot--
            }
            i++
        }
        println(if (ans == Int.MAX_VALUE) -1 else ans)
    }
}