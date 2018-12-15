package archive.er56

fun main(args: Array<String>) {
    val t = readLine()!!.toInt()
    repeat(t) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val g = Array(n) { ArrayList<Int>() }
        repeat(m) {
            val (u, v) = readLine()!!.split(" ").map { it.toInt() }
            g[u - 1].add(v - 1)
            g[v - 1].add(u - 1)
        }
        println(solveGraph(n, g))
    }
}

private const val MODULO = 998244353L

fun add(a: Long, b: Long) = (a + b) % MODULO
fun mul(a: Long, b: Long) = (a * b) % MODULO

fun pow(a: Long, b: Int): Long {
    var cur = 1L
    repeat(b) { cur = mul(cur, a) }
    return cur
}

fun solveGraph(n: Int, g: Array<ArrayList<Int>>): Long {
    val c = IntArray(2)
    val d = IntArray(n) { -1 }
    
    fun visit(i: Int, di: Int): Boolean {
        d[i] = di
        c[di % 2]++
        for (j in g[i]) {
            val dj = d[j]
            if (dj >= 0) {
                if ((di % 2) == (dj % 2)) return false
            } else {
                if (!visit(j, di + 1)) return false
            }
        }
        return true
    }

    var res = 1L
    for (i in 0 until n) {
        if (d[i] < 0) {
            if (!visit(i, 0)) return 0L
            res = mul(res, add(pow(2L, c[0]), pow(2L, c[1])))
            c[0] = 0
            c[1] = 0
        }
    }
    return res
}
