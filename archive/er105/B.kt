package archive.er105

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, u, r, d, l) = readLine()!!.split(" ").map { it.toInt() }
        println(if(solveB(n, u, r, d, l)) "YES" else "NO")
    }
}

fun solveB(n: Int, u: Int, r: Int, d: Int, l: Int): Boolean {
    for (i in 0..15) {
        val ul = if ((i and 1) != 0) 1 else 0
        val ur = if ((i and 2) != 0) 1 else 0
        val dl = if ((i and 4) != 0) 1 else 0
        val dr = if ((i and 8) != 0) 1 else 0
        if (
            u in ul + ur..n - 2 + ur + ul &&
            d in dr + dl..n - 2 + dr + dl &&
            l in ul + dl..n - 2 + ul + dl &&
            r in ur + dr..n - 2 + ur + dr
        ) return true
    }
    return false
}
