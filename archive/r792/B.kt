fun main() {
    repeat(readLine()!!.toInt()) {
        val (a, b, c) = readLine()!!.split(" ").map { it.toLong() }
        val z = c
        val y = c + b
        val x = c + b + a
        println("$x $y $z")
    }
}