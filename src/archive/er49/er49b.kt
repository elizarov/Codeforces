package archive.er49

fun main(args: Array<String>) {
    val (n, q) = readLine()!!.split(" ").map { it.toInt() }
    val a = LongArray(q) {
        val (x, y) = readLine()!!.split(" ").map { it.toLong() }
        queryBoard(x, y, n.toLong())
    }
    println(a.joinToString("\n"))
}

fun queryBoard(x: Long, y: Long, n: Long): Long {
    return if ((x + y) % 2 == 0L) {
        if (x % 2 != 0L) {
            x / 2 * n + y / 2 + 1
        } else {
            (x - 1) / 2 * n + (y - 1) / 2 + (n + 1) / 2 + 1
        }
    } else {
        val b = (n * n + 1) / 2 + 1
        if (x % 2 != 0L) {
            x / 2 * n + (y - 1) / 2 + b
        } else {
            (x - 1) / 2 * n + (y - 1) / 2 + n / 2 + b
        }
    }
}