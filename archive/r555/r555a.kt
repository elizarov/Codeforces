package archive.r555
//tailrec fun removeZeroes(x: Int): Int = if (x % 10 == 0) removeZeroes(x / 10) else x
//fun archive.r555.f(x: Int) = removeZeroes(x + 1)

fun f(x: Int): Int {
    var cur = x + 1
    while (cur % 10 == 0) cur /= 10
    return cur
}

fun main() {
    var n = readLine()!!.toInt() // read integer from the input
    val reached = HashSet<Int>() // a mutable hash set
    while (reached.add(n)) n = f(n) // iterate function archive.r555.f
    println(reached.size) // print answer to the output
}
