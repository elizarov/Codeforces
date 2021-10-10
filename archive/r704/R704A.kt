package archive.r704

fun main() {
    repeat(readLine()!!.toInt()) {
        val d0 = readLine()!!.split(" ").map { it.toLong() }
        val p = d0[0]
        val d = d0.drop(1)
        val ans = d.minOf { a -> a - (p - 1) % a - 1 }
        println(ans)
    }
}