fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        println(when(solveB(n, a)) {
            1 -> "Mike"
            2 -> "Joe"
            else -> error("!!")
        })
    }
}

fun solveB(n: Int, a: List<Int>): Int {
    if (n % 2 == 1) return 1
    val m = a.minOrNull()!!
    val i = a.indexOf(m)
    return if (i % 2 == 0) 2 else 1
}
