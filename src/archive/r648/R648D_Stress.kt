package archive.r648

fun main() {
    val n = 50
    val a = Array(n) { CharArray(n) { '.' } }
    check(solveD(n, n, a))
}