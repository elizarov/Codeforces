package archive.y2021.deltixSummer

fun main() {
    repeat(readLine()!!.toInt()) {
        val (c, d) = readLine()!!.split(" ").map { it.toInt() }
        val ans = when {
            c == 0 && d == 0 -> 0
            c + d == 0 -> 1
            c > 0 && c == d -> 1
            (c - d) % 2 == 0 && (c + d) / 2 > 0 -> 2
            else -> -1
        }
        println(ans)
    }
}