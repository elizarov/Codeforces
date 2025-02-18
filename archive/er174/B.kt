fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        val cc = IntArray(n * m + 1)
        fun get(i: Int, j: Int) = if (i in 0..<n && j in 0..<m) a[i][j] else 0
        for (i in 0..<n) for (j in 0..<m) {
            val c = a[i][j]
            if (cc[c] == 0) cc[c] = 1
            if (get(i - 1, j) == c || get(i + 1, j) == c || get(i, j - 1) == c || get(i, j + 1) == c) cc[c] = 2
        }
        val ans = cc.filter { it != 0 }.sorted().dropLast(1).sum()
        println(ans)
    }
}