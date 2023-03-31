fun main() {
    repeat(readln().toInt()) {
        val (l, r) = readln().split(" ").map { it.toInt() }
        println(solveA(l, r))

    }
}

private fun luck(x: Int): Int {
    var r = x
    var min = 10
    var max = 0
    while (r != 0) {
        val d = r % 10
        r /= 10
        min = minOf(d, min)
        max = maxOf(d, max)
    }
    return max - min
}

fun solveA(l: Int, r: Int): Int {
    var x = l / 100 * 100 + 90
    if (x < l) x += 100
    if (x <= r) return x
    return (l..r).maxBy { luck(it) }
}
