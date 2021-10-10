package archive.gr7

fun main() {
    val n = readLine()!!.toInt()
    val b = readLine()!!.split(" ").map { it.toInt() }
    var x = 0
    val a = IntArray(n) { i -> (b[i] + x).also { x = maxOf(x, it) } }
    println(a.joinToString(" "))
}