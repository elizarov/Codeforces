import kotlin.math.*

fun main() {
    fun cnt(n: Int): Int = when (n) {
            1 -> 1
            2 -> 2
            3 -> 4
            else -> 2 * cnt(n - 1) + 1
        }
    for (n in 1..20) {
        val c = cnt(n)
        val l = ceil((1 shl (n + 1)) / 3.0).toInt()
        val ok = if (c <= l) "Ok" else "FAIL!!!"
        println("$n $c $l $ok")
    }
}