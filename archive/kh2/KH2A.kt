package archive.kh2

fun main() {
    val n = readLine()!!.toInt()
    val r = readLine()!!.split(" ").map { it.toInt() }
        .withIndex()
        .distinctBy { it.value }
        .sortedBy { it.value }
        .map { it.index + 1 }
    if (r.size < 3) {
        println("-1 -1 -1")
    } else {
        println("${r[0]} ${r[1]} ${r[2]}")
    }
}