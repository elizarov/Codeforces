import java.util.TreeSet

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val p = solveD(n) { a, b ->
            println("? $a $b")
            val c = readln().toInt()
            check(c in 0..1)
            c == 1
        }
        println("! ${p.joinToString(" ")}")
    }
}

fun solveD(n: Int, ask: (Int, Int) -> Boolean): List<Int> {
    val p = IntArray(n)
    var w = 1
    while (ask(1, w + 1)) w++
    val last = IntArray(w + 1) { it }
    fun save(i: Int, j: Int) {
        p[i] = last[j]
        last[j] = i
    }
    save(w + 1, 1)
    val remaining = TreeSet<Int>((1..w).toList())
    var j = 1
    for (i in w + 2..<n) {
        while (true) {
            j = remaining.tailSet(j + 1).firstOrNull() ?: remaining.first()
            if (ask(j, i)) {
                remaining.remove(j)
            } else {
                save(i, j)
                break
            }
        }
    }
    return p.drop(1)
}