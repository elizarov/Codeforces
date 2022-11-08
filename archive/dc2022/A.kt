fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val c = readln().groupingBy { it }.eachCount().toMutableMap()
        val m = n / k
        val res = CharArray(k) { i ->
            var last = 'a'
            for (j in 1..m) {
                val cnt = c[last] ?: 0
                if (cnt == 0) break
                c[last] = cnt - 1
                last++
                if (last == 'z') break
            }
            last
        }
        println(res.concatToString())
    }
}