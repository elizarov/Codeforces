import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val (x, y) = readln().split(" ").map { it.toInt() }
        val c = min(x, y)
        println((n + c - 1) / c)
        
    }
}