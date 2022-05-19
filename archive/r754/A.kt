fun main() {
    repeat(readLine()!!.toInt()) {
        val (a1, a2, a3) = readLine()!!.split(" ").map { it.toInt() }
        val s = a1 + a3
        val d = s - 2 * a2
        println(if (d % 3 == 0) 0 else 1)
    }
}