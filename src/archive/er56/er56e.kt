package archive.er56

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.splitToIntArray()
    val b = readLine()!!.splitToIntArray()

    val aa = IntArray(n + 1)
    for (i in 0 until n)
        aa[a[i]] = i

    val bb = IntArray(n + 1)
    for (i in 0 until n)
        bb[b[i]] = i

    val k = sqrt(n.toDouble()).roundToInt()
    val d = IntArray(k + 1) { i -> i * n / k }

    val t = Array(4 * k) { IntArray(4 * k) }

    fun count(u: Int, v: Int): Int {
        val l = d[v]
        val r = d[v + 1]
        var cnt = 0
        for (i in d[u] until d[u + 1])
            if (bb[a[i]] in l until r)
                cnt++
        return cnt
    }

    fun build(i: Int, j: Int, tal: Int, tar: Int, tbl: Int, tbr: Int, tbu: Int): Int {
        val da = tar - tal
        val db = tbr - tbl
        val res = if (da == 0 && db == 0) {
            count(tal, tbl)
        } else if (da > db) {
            val tam = (tal + tar) / 2
            build(2 * i + 1, j, tal, tam, tbl, tbr, tbu) +
            build(2 * i + 2, j, tam + 1, tar, tbl, tbr, tbu)
        } else {
            val tbm = (tbl + tbr) / 2
            if (tbu < 0 || tbu <= tbm) build(i, 2 * j + 1, tal, tar, tbl, tbm, tbu)
            if (tbu < 0 || tbu > tbm) build(i, 2 * j + 2, tal, tar, tbm + 1, tbr, tbu)
            t[i][2 * j + 1] + t[i][2 * j + 2]
        }
        t[i][j] = res
        return res
    }

    fun query(i: Int, j: Int, al: Int, ar: Int, bl: Int, br: Int, tal: Int, tar: Int, tbl: Int, tbr: Int): Int {
        if (al > ar || bl > br) return 0
        if (al == tal && ar == tar && bl == tbl && br == tbr) return t[i][j]
        val da = tar - tal
        val db = tbr - tbl
        return if (da > db) {
            val tam = (tal + tar) / 2
            query(2 * i + 1, j, al, min(tam, ar), bl, br, tal, tam, tbl, tbr) +
            query(2 * i + 2, j, max(al, tam + 1), ar, bl, br, tam + 1, tar, tbl, tbr)
        } else {
            val tbm = (tbl + tbr) / 2
            query(i, 2 * j + 1, al, ar, bl, min(tbm, br), tal, tar, tbl, tbm) +
            query(i, 2 * j + 2, al, ar, max(tbm + 1, bl), br, tal, tar, tbm + 1, tbr)
        }
    }

    build(0, 0, 0, k - 1, 0, k - 1, -1)

    repeat(m) {
        val s = readLine()!!.split(" ").map { it.toInt() }
        when (s[0]) {
            1 -> {
                val (la, ra, lb, rb) = s.subList(1, 5).map { it - 1 }
                val al = d.left(la)
                val ar = d.right(ra)
                val bl = d.left(lb)
                val br = d.right(rb)
                var cnt = query(0, 0, al, ar, bl, br, 0, k - 1, 0, k - 1)
                val na = if (al <= ar) d[al] - 1 else ra
                for (i in la..na)
                    if (bb[a[i]] in lb..rb) cnt++
                if (al <= ar) for (i in max(d[ar + 1], la)..ra)
                    if (bb[a[i]] in lb..rb) cnt++
                val nb = if (bl <= br) d[bl] - 1 else rb
                for (j in lb..nb)
                    if (aa[b[j]] in d[al] until d[ar + 1]) cnt++
                if (bl <= br) for (j in max(d[br + 1], lb)..rb)
                    if (aa[b[j]] in d[al] until d[ar + 1]) cnt++
                println(cnt)
            }
            2 -> {
                val (x, y) = s.subList(1, 3).map { it - 1 }
                b[x] = b[y].also { b[y] = b[x] }
                bb[b[x]] = x
                bb[b[y]] = y
                build(0, 0, 0, k - 1, 0, k - 1, d.right(x))
                build(0, 0, 0, k - 1, 0, k - 1, d.right(y))
            }
        }
    }
}

private fun IntArray.left(x: Int): Int {
    val i = binarySearch(x)
    return if (i >= 0) i else -i - 1
}

private fun IntArray.right(x: Int): Int {
    val i = binarySearch(x)
    return if (i >= 0) i - 1 else -i - 3
}

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
            require(d >= 0 && d <= 9) { "Unexpected character '$c' at $i" }
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

/*
6 1
5 1 4 2 3 6
5 1 3 2 4 6
1 4 4 1 3
*/
