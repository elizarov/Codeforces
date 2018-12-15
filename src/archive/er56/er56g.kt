package archive.er56

import kotlin.math.*

fun eval(x: IntArray, v: Int): Int {
    var sum = 0
    for (i in x.indices) {
        if (v and (1 shl i) == 0)
            sum += x[i]
        else
            sum -= x[i]
    }
    return sum
}

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) {
        readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }
    val m = 1 shl k
    val t = kotlin.arrayOfNulls<IntArray>(4 * n)

    fun build(i: Int, tu: Int, tl: Int, tr: Int) {
        val res = IntArray(m)
        if (tl == tr) {
            for (v in 0 until m) {
                res[v] = eval(a[tl], v)
            }
        } else {
            val tm = (tl + tr) / 2
            if (tu < 0 || tu <= tm) build(2 * i + 1, tu, tl, tm)
            if (tu < 0 || tu >= tm + 1) build(2 * i + 2, tu, tm + 1, tr)
            val r1 = t[2 * i + 1]!!
            val r2 = t[2 * i + 2]!!
            for (v in 0 until m) {
                res[v] = max(r1[v], r2[v])
            }
        }
        t[i] = res
    }

    build(0, -1,0, n - 1)

    val res = IntArray(m)

    fun compute(i: Int, l: Int, r: Int, tl: Int, tr: Int) {
        if (l > r) return
        if (l == tl && r == tr) {
            val ri = t[i]!!
            for (v in 0 until m) {
                res[v] = max(res[v], ri[v])
            }
            return
        }
        val tm = (tl + tr) / 2
        compute(2 * i + 1, l, min(r, tm), tl, tm)
        compute(2 * i + 2, max(l, tm + 1), r, tm + 1, tr)
    }

    val q = readLine()!!.toInt()
    repeat(q) {
        val s = readLine()!!.split(" ").map { it.toInt() }
        when (s[0]) {
            1 -> {
                val i = s[1]
                val b = s.subList(2, 2 + k).toIntArray()
                a[i - 1] = b
                build(0, i - 1, 0, n - 1)
            }
            2 -> {
                val l = s[1]
                val r = s[2]
                res.fill(Int.MIN_VALUE)
                compute(0, l - 1, r - 1, 0, n - 1)
                var ans = 0
                for (v in 0 until m) {
                    val z = abs(res[v] + res[v xor (m - 1)])
                    ans = max(ans, z)
                }
                println(ans)
            }
        }
    }
}
