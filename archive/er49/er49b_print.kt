package archive.er49

fun main(args: Array<String>) {
    val n = 5L
    for (x in 1..n) {
        println((1..n).map { y -> queryBoard(x, y, n) }.joinToString(" "))
    }
}