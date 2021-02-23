import kotlin.math.*

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val x = readLine()!!.split(" ").map { it.toInt() }
        val p = HashSet<Int>()
        for (x1 in x) for (x2 in x) if (x1 != x2) p.add((x1 - x2).absoluteValue)
        println(p.size)
    }
}