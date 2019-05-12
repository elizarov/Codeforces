package archive.r554

import java.io.*

fun main() = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    val n = readLine()!!.toInt()
    val b = readLine()!!.splitToIntArray()
    val c = readLine()!!.splitToIntArray()
    val g = HashMap<Int, HashMap<Int, Int>>()
    var ok = true
    for (i in 0..n - 2) {
        val x = b[i]
        val y = c[i]
        if (x > y) ok = false
        g.addEdge(x, y)
        g.addEdge(y, x)
    }
    val s = if (!ok) null else solve(g)
    if (s == null || s.size != n) {
        println(-1)
    } else {
        println(s.joinToString(" "))
    }
}

private fun HashMap<Int, HashMap<Int, Int>>.addEdge(x: Int, y: Int) {
    val xm = getOrPut(x) { HashMap(2) }
    xm[y] = xm.getOrDefault(y, 0) + 1
}

private fun HashMap<Int, HashMap<Int, Int>>.removeEdge(x: Int, y: Int) {
    val xm = get(x) ?: return
    val u = xm[y]!! - 1
    if (u == 0) xm.remove(y) else xm[y] = u
    if (xm.isEmpty()) remove(x)
}

private fun solve(g: HashMap<Int, HashMap<Int, Int>>): List<Int>? {
    val odd = g.filter { entry -> entry.value.values.sum() % 2 != 0 }.keys
    if (odd.size > 2) return null
    val res = ArrayList<Int>()
    val st = ArrayList<Int>()
    st.add(odd.firstOrNull() ?: g.keys.first()!!)
    while (st.isNotEmpty()) {
        val x = st[st.lastIndex]
        val xm = g[x]
        if (xm == null) {
            st.removeAt(st.lastIndex)
            res.add(x)
        } else {
            val y = xm.entries.firstOrNull()?.key ?: break
            g.removeEdge(x, y)
            g.removeEdge(y, x)
            st.add(y)
        }
    }
    return res
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }

private fun String.splitToIntArray(): IntArray {
    val n = length
    if (n == 0) return IntArray(0) // EMPTY
    var res = IntArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0
        var neg = false
        var c = get(i) // expecting number, IOOB if there is no number
        if (c == '-') {
            neg = true
            i++
            c = get(i) // expecting number, IOOB if there is no number
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d in 0..9) { "Unexpected character '$c' at $i" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $i" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $i" }
            i++
            if (i >= n) break
            c = get(i)
            if (c == ' ') break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m++] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $i" } }
        if (i >= n) break
        i++
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}