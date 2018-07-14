package r491

fun main(args: Array<String>) {
    val (a, b, c, n) = readLine()!!.split(" ").map { it.toInt() }
    if (c > a || c > b) {
        println(-1)
        return
    }
    val k = a + b - c
    if (k >= n) {
        println(-1)
        return
    }
    println(n - k)
}