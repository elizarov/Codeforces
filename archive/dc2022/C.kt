fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val rc = readln().split(" ").map { it.toInt() }
        val (x, y) = readln().split(" ").map { it.toInt() }
        val rs = rc.filterIndexed { index, _ -> index % 2 == 0 }
        val cs = rc.filterIndexed { index, _ -> index % 2 == 1 }
        val r = rs.sorted()[1]
        val c = cs.sorted()[1]
        val rb = r == 1 || r == n
        val cb = c == 1 || c == n
        val ans = if (rb && cb) {
            x == r || y == c
        } else {
            (r - x) % 2 == 0 || (c - y) % 2 == 0
        }
        println(if (ans) "YES" else "NO")
    }
}