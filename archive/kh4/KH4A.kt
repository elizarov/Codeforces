package archive.kh4

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val n1 = n / (1 + k + k * k + k * k * k)
        val n2 = n1 * k
        val n3 = n2 * k
        val n4 = n3 * k
        println("$n1 $n2 $n3 $n4")
    }
}