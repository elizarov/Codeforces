package archive.er105

fun main() {
    repeat(readLine()!!.toInt()) {
        val a = readLine()!!
        println(if (solveA(a)) "YES" else "NO")
    }
}

private fun solveA(a: String): Boolean {
    check@for (i in 0..7) {
        val b = a
            .replace('A', if ((i and 1) != 0) '(' else ')')
            .replace('B', if ((i and 2) != 0) '(' else ')')
            .replace('C', if ((i and 4) != 0) '(' else ')')
        var bal = 0
        for (c in b) {
            when (c) {
                '(' -> bal++
                ')' -> {
                    if (bal == 0) continue@check
                    bal--
                }
                else -> error("!!")
            }
        }
        if (bal == 0) return true
    }
    return false
}