fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val c0 = a.count { it == 0 }
        val c1 = a.count { it == 1 }
        val res = when {
            n - c0 >= c0 - 1 -> 0
            c1 + c0 < n || c0 == n -> 1
            else -> 2
        }
        println(res)
    }
}