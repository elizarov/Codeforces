fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, d) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }.sorted()
        val ok = a.last() <= d || a[0] + a[1] <= d
        println(if (ok) "YES" else "NO")
    }
}