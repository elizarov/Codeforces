package archive.er78

import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        val diff = abs(a - b)
        var n = ceil((sqrt(8.0 * diff + 1.0) - 1) / 2).toInt()
        while (true) {
            val x = n * (n + 1) / 2
            check(x >= diff)
            if ((x - diff) % 2 == 0) {
                println(n)
                break
            }
            n++
        }
    }
}