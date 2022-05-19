import kotlin.random.*

fun main() {
    val (n, d) = readLine()!!.split(" ").map { it.toInt() }
    val c = IntArray(n) { it }
    val cd = IntArray(n) { 0 }
    var m = 1
    val s = SS()
    val cn = Array(n) { Node(1) }
    for (i in 0 until n) s.add(cn[i])
    fun join(cx: Int, cy: Int) {
        c[cx] = cy
        s.remove(cn[cx])
        s.remove(cn[cy])
        cn[cy].x += cn[cx].x
        cn[cy].sum += cn[cx].x
        s.add(cn[cy])
    }
    repeat(d) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        var cx = x; while (c[cx] != cx) cx = c[cx]
        var cy = y; while (c[cy] != cy) cy = c[cy]
        if (cx == cy) {
            m++
        } else {
            if (cd[cx] < cd[cy]) {
                join(cx, cy)
            } else {
                join(cy, cx)
                if (cd[cy] == cd[cx]) cd[cx]++
            }
        }
        println(s.sumMax(m) - 1)
    }
}

class Node(var x: Int) {
    val y: Int = Random.nextInt()
    var l: Node? = null
    var r: Node? = null
    var p: Node? = null
    var cnt: Int = 1
    var sum: Int = x

    fun update() {
        cnt = 1 + (l?.cnt ?: 0) + (r?.cnt ?: 0)
        sum = x + (l?.sum ?: 0) + (r?.sum ?: 0)
    }
}

fun split(t: Node?, k: Int): Pair<Node?, Node?> {
    if (t == null) return Pair(null, null)
    if (k > t.x) {
        val (t1, t2) = split(t.r, k)
        t.r = t1
        t.update()
        t1?.p = t
        t2?.p = null
        t.p = null
        return Pair(t, t2)
    } else {
        val (t1, t2) = split(t.l, k)
        t.l = t2
        t.update()
        t2?.p = t
        t1?.p = null
        t.p = null
        return Pair(t1, t)
    }
}

fun merge(t1: Node?, t2: Node?): Node? {
    if (t2 == null) return t1
    if (t1 == null) return t2
    if (t1.y > t2.y) {
        val t = merge(t1.r, t2)
        t1.r = t
        t1.update()
        t?.p = t1
        return t1
    } else {
        val t = merge(t1, t2.l)
        t2.l = t
        t2.update()
        t?.p = t2
        return t2
    }
}

data class CS(val cnt: Int, val sum: Int)

fun Node?.sumMax(m: Int): CS {
    if (this == null) return CS(0, 0)
    if (cnt <= m) return CS(cnt, sum)
    val rs = r.sumMax(m)
    if (rs.cnt >= m) return rs
    val rest = m - rs.cnt
    if (rest == 1) return CS(rs.cnt + 1, rs.sum + x)
    val ls = l.sumMax(rest - 1)
    return CS(ls.cnt + 1 + rs.cnt, ls.sum + x + rs.sum)
}

class SS {
    private var root: Node? = null

    fun add(n: Node) {
        val (t1, t2) = split(root, n.x)
        val t = merge(merge(t1, n), t2)
        root = t
        t?.p = null
    }

    fun remove(n: Node) {
        val t = merge(n.l, n.r)
        val p = n.p
        when {
            p == null -> {
                root = t
                t?.p = null
            }
            p.l == n -> {
                p.l = t
                t?.p = p
            }
            p.r == n -> {
                p.r = t
                t?.p = p
            }
            else -> error("!")
        }
        n.l = null
        n.r = null
        n.p = null
        n.cnt = 1
        n.sum = n.x
        var cur = p
        while (cur != null) {
            cur.update()
            cur = cur.p
        }
    }

    fun sumMax(m: Int): Int = root.sumMax(m).sum
}