package er46

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { ArrayList<Int>() }
    val b = Array(n) { HashSet<Int>() }
    repeat(m) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[x].add(y)
        g[y].add(x)
    }
    val v = BooleanArray(n)
    val t = IntArray(n)
    val f = IntArray(n)
    var cnt = 0

    fun dfsBridges(i: Int, p: Int) {
        cnt++
        v[i] = true
        t[i] = cnt
        f[i] = cnt
        for (j in g[i]) {
            if (j == p) continue
            if (v[j]) {
                f[i] = min(f[i], t[j])
            } else {
                dfsBridges(j, i)
                f[i] = min(f[i], f[j])
                if (f[j] > t[i]) { // bridge
                    //println("b: ${i+1} ${j+1}")
                    b[i].add(j)
                    b[j].add(i)
                }
            }
        }
    }

    dfsBridges(0, -1)
    var answer = 0
    v.fill(false)
    
    fun dfsMaxLen(i: Int): Int {
        v[i] = true
        val bi = b[i]
        val res = ArrayList<Int>()
        for (j in g[i]) {
            if (v[j]) continue
            val d = if (j in bi) 1 else 0
            res.add(d + dfsMaxLen(j))
        }
        if (res.isEmpty()) return 0
        res.sort()
        val k = res.size
        if (k >= 2) {
            answer = max(answer, res[k - 1] + res[k - 2])
        }
        return res[k - 1]
    }

    val res = dfsMaxLen(0)
    answer = max(res, answer)
    println(answer)
}

