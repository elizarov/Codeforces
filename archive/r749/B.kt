fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        data class D(val a: Int, val b: Int, val c: Int)
        val d = Array(m) {
            val (a, b, c) = readLine()!!.split(" ").map { it.toInt() }
            D(a, b, c)
        }
        val bs = d.map { it.b }.toSet()
        val center = (1..n).first { it !in bs }
        for (i in 1..n) {
            if (i == center) continue
            println("$i $center")
        }
    }
}