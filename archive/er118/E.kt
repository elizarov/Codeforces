val dx = intArrayOf(1, 0, -1, 0)
val dy = intArrayOf(0, 1, 0, -1)

fun main() {
    val ans = Array(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val a = Array(n) { readLine()!!.toCharArray() }
        val sz = n * m
        val qx = IntArray(sz)
        val qy = IntArray(sz)
        var qh = 0
        var qt = 0
        val u = Array(n) { BooleanArray(m) }
        fun enq(i: Int, j: Int) {
            if (u[i][j]) return
            qx[qt] = i
            qy[qt] = j
            qt++
            if (qt > sz) qt = 0
            u[i][j] = true
        }
        for (i in 0 until n) for (j in 0 until m) if (a[i][j] == 'L') {
            enq(i, j)
        }
        while (qh != qt) {
            val i0 = qx[qh]
            val j0 = qy[qh]
            qh++
            if (qh > sz) qh = 0
            u[i0][j0] = false
            for (k in 0..3) {
                val i = i0 + dx[k]
                val j = j0 + dy[k]
                if (i < 0 || i >= n || j < 0 || j >= m || a[i][j] != '.') continue
                var oc = 0
                for (t in 0..3) {
                    val i1 = i + dx[t]
                    val j1 = j + dy[t]
                    if (i1 < 0 || i1 >= n || j1 < 0 || j1 >= m || a[i1][j1] == '#' || a[i1][j1] == '+' || a[i1][j1] == 'L') oc++
                }
                if (oc >= 3) {
                    a[i][j] = '+'
                    enq(i, j)
                }
            }
        }
        (0 until n).joinToString("\n") { a[it].concatToString() }
    }
    println(ans.joinToString("\n"))
}