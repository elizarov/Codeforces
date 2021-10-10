package archive.kh1

fun main() {
    val (n, m) = readInts()
    val a = readInts().toIntArray()
    val q = readInt()
    val b = IntArray(4 * a.size)

    fun buildTree(il: Int, ir: Int, p: Int): Int {
        val s = if (il == ir) {
            a[il]
        } else {
            val im = (il + ir) / 2
            buildTree(il, im, 2 * p + 1) + buildTree(im + 1, ir, 2 * p + 2)
        }
        b[p] = s
        return s
    }

    buildTree(0, n - 1, 0)
    val ans = List(q) {
        val w = readInts().drop(1) + (m + 1) // sentinel
        if (solveDoors(n, m, b, w)) "YES" else "NO"
    }.joinToString("\n")
    println(ans)
}

private fun solveDoors(n: Int, m: Int, b: IntArray, w: List<Int>): Boolean {
    var pp = 0
    var i = 0
    var rem = 0

    fun queryTree(il: Int, ir: Int, p: Int, i: Int): Int {
        if (il == i && rem >= b[p]) {
            rem -= b[p]
            return ir + 1
        }
        if (il == ir) return i
        val im = (il + ir) / 2
        if (i > im) return queryTree(im + 1, ir, 2 * p + 2, i)
        val j = queryTree(il, im,2 * p + 1, i)
        if (j <= im) return j
        return queryTree(im + 1, ir,2 * p + 2, im + 1)
    }

    for (cp in w) {
        rem = cp - pp - 1
        i = queryTree(0, n - 1, 0, i)
        if (i >= n) return true
        pp = cp
    }
    return false
}

private fun readInt() = readLine()!!.toInt()
private fun readInts() = readLine()!!.split(" ").map { it.toInt() }