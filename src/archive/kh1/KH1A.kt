package archive.kh1

fun main() {
    val q = readLine()!!.toInt()
    repeat(q) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }.sorted()
        println("1 ${x - 1} ${y - x + 1}")
    }
}