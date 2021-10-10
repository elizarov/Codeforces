package archive.r538

fun main() {
    val (x, y, z) = readLine()!!.split(' ').map { it.toInt() }
    val (a, b, c) = readLine()!!.split(' ').map { it.toInt() }
    if (a >= x && a - x + b >= y && a - x + b - y + c >= z) {
        println("YES")
    } else {
        println("NO")
    }
}