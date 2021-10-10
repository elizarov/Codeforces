package r482d

import java.util.*

const val MAX = 10_000

class Pr {
    val p = IntArray(MAX)
    val n: Int

    init {
        p[0] = 2
        var n = 1
        var k = 1
        loop@ while (true) {
            k += 2
            if (k > MAX) break
            for (i in 1 until n) {
                if (p[i] * p[i] > k) break
                if (k % p[i] == 0) continue@loop
            }
            p[n++] = k
        }
        this.n = n
    }

    fun fact(u: Int): Map<Int, Int> {
        val f = HashMap<Int,Int>()
        var r = u
        for (i in 0 until n) {
            if (r == 1) break
            if (p[i] * p[i] > r) {
                f[r] = 1
                break
            }
            var cnt = 0
            while (r % p[i] == 0) {
                cnt++
                r /= p[i]
            }
            if (cnt > 0) f[p[i]] = cnt
        }
        return f
    }
}

fun cons(p: Int, c: Int) = (p shl 5) + c

class St {
    val pr = Pr()
    val a = TreeSet<Int>()
    val cs = HashMap<Int, TreeSet<Int>>()

    fun add(u: Int) {
        if (!a.add(u)) return
        val f = pr.fact(u)
        for ((p, c) in f) {
            for (n in 0 until c)
                cs.getOrPut(cons(p, n)) { TreeSet<Int>() } += u
        }
    }

    fun query(x: Int, k: Int, s: Int): Int {
        val limit = s - x
        var best = -1
        var max = -1
        val xf = pr.fact(x)
        val kf = pr.fact(k)
        val c = ArrayList<SortedSet<Int>>()
        for ((xp, xc) in xf) {
            val kc = kf[xp] ?: 0
            if (kc < xc) {
                val cc = cs[cons(xp, kc)]
                if (cc != null) {
                    val hs = cc.headSet(limit, true)
                    if (!hs.isEmpty()) c += hs
                }
            }
        }
        val t = a.headSet(limit, true)
        find@ for (v in t) {
            for (cc in c) if (v in cc) continue@find
            val target = x xor v
            if (target > max) {
                max = target
                best = v
            }
        }
        return best
    }
}


fun main(args: Array<String>) {
    val q = readLine()!!.toInt()
    val st = St()
    repeat(q) {
        val line = readLine()!!.split(" ")
        when (line[0]) {
            "1" -> {
                 st.add(line[1].toInt())
            }
            "2" -> {
                val x = line[1].toInt()
                val k = line[2].toInt()
                val s = line[3].toInt()
                println(st.query(x, k, s))
            }
            else -> error(line[0])
        }
    }
}