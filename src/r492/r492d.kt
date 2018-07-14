package r492

import kotlin.math.*

fun main(args: Array<String>) {
//    val (n, r) = readLine()!!.r492.er47.splitToIntArray()
//    val c = readLine()!!.r492.er47.splitToIntArray()
    val n = 18
    var c = IntArray(1 shl n)

    var pc = 0
    val m = 1 shl n
    val fi = IntArray(m)
    val ns = IntArray(m)
    val vc = IntArray(m)
    val pr = arrayOfNulls<IntArray?>(m)
    for (mask in 0 until m) {
        val nf = Integer.bitCount(mask)
        ns[mask] = n - nf
        fi[mask] = pc
        vc[mask] = 1 shl ns[mask]
        pc += vc[mask]
        val prm = IntArray(2 * nf)
        var rem = mask
        var k = 0
        while (rem != 0) {
            val hb = Integer.highestOneBit(rem)
            prm[k] = mask and hb.inv()
            prm[k + 1] = Integer.bitCount(mask and (hb - 1))
            k += 2
            rem = rem and hb.inv()
        }
        check(k == 2 * nf)
        pr[mask] = prm
    }

//    println(pc)
    c = c.copyOf(pc)

    val rev = Array<Rev>(pc) { Rev() }
    for (i in 0 until m)
        rev[m].a = IntArray(4 * n)

    for (mask in 1 until m) {
        val prm = pr[mask]!!
        for (v in 0 until vc[mask]) {
            updateOne(mask, v, c, fi, prm, rev, true)
            rev[fi[mask] + v].a = IntArray(4 * ns[mask])
        }
    }
    println(c[pc - 1])

//    repeat(r) {
//        val (z, g) = readLine()!!.r492.er47.splitToIntArray()
//        d[z] = g.toDouble()
//
//    }
}

private class Rev(var c: Int = 0, var a: IntArray? = null) {
    fun add(mask: Int, v: Int) {
        val a = a!!
        a[c++] = mask
        a[c++] = v
    }
}

private fun updateOne(
    mask: Int,
    v: Int,
    c: IntArray,
    fi: IntArray,
    prm: IntArray,
    rev: Array<Rev>,
    buildRev: Boolean
) {
    var minD = Int.MIN_VALUE
    var maxD = Int.MIN_VALUE
    for (k in 0 until prm.size step 2) {
        val pm = prm[k]
        val ps = prm[k + 1]
        val psm = (1 shl ps) - 1
        val nj = ((v and psm.inv()) shl 1) or (v and psm)
        val p0 = c[fi[pm] + nj]
        val p1 = c[fi[pm] + nj + (1 shl ps)]
        if (buildRev) {
            rev[fi[pm] + nj].add(mask, v)
            rev[fi[pm] + nj + (1 shl ps)].add(mask, v)
        }
        minD = min(minD, min(p0, p1))
        maxD = max(maxD, max(p0, p1))
    }
    c[fi[mask] + v] = minD + maxD
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