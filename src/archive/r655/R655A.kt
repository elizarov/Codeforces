package archive.r655

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        println(List(n) { 1 }.joinToString(" "))
    }
}