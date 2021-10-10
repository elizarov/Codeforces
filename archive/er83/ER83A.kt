package archive.er83

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        if (n % m == 0) println("YES") else println("NO")
    }
}