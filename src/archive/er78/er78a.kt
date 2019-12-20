package archive.er78

fun main() {
    repeat(readLine()!!.toInt()) {
        val p = readLine()!!.toList().sorted()
        val h = readLine()!!.toList()
        val ans = (0..h.size - p.size).any { i ->
            h.subList(i, i + p.size).sorted() == p
        }
        println(if (ans) "YES" else "NO")
    }
}