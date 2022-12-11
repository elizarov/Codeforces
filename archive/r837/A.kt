fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val min = a.min()
        val max = a.max()
        val c1 = a.count { it == min }.toLong()
        val c2 = a.count { it == max }.toLong()
        val ans = if (c1 + c2 <= n) 2 * c1 * c2 else c1 * (c1 - 1)
        println(ans)
    }
}