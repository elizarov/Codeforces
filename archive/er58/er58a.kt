package archive.er58

fun main() {
    val q = readLine()!!.toInt()
    repeat(q) {
        val (l, r, d) = readLine()!!.split(" ").map { it.toLong() }
        if (d < l) {
            println(d)
        } else {
            println((r / d + 1) * d)
        }
    }
}