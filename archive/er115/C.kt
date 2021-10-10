fun main() {
    repeat(readLine()!!.toInt()) test@{
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val s = a.sumOf { it.toLong() }
        if (2 * s % n != 0L) {
            println(0)
            return@test
        }
        val psl = 2 * s / n
        if (psl > Int.MAX_VALUE) {
            println(0)
            return@test
        }
        val ps = psl.toInt()
        val vc = a.groupingBy { it }.eachCount()
        var cc = 0L
        for ((v1, c1) in vc) {
            val v2 = ps - v1
            if (v1 != v2) {
                val c2 = vc[v2] ?: continue
                cc += c1.toLong() * c2
            } else {
                cc += c1.toLong() * (c1 - 1)
            }
        }
        println(cc / 2)
    }
}