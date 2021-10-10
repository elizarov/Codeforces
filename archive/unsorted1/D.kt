package archive.unsorted1

import kotlin.math.*

fun main() {
    val (n, m, p) = readLine()!!.split(" ").map { it.toInt() }
    val b = LongArray(n) {
        val s = readLine()!!
        var sum = 0L
        for (j in 0 until m) {
            if (s[j] == '1') sum += 1L shl j
        }
        sum
    }
    val ans = solveD(n, m, b)
    CharArray(m) { j ->
        (if ((ans and (1L shl j)) != 0L) '1' else '0')
    }.concatToString().let { println(it) }

}

fun solveD(n: Int, m: Int, b: LongArray): Long {
    val t = (n + 1) / 2
    var l0 = arrayListOf(0L)
    var l1 = arrayListOf<Long>()
    val cs = LLHashMap()
    var bs = 1
    while (true) {
        if (bs == 1) {
            for (j in 0 until m) {
                val mask = 1L shl j
                var cnt = 0
                for (i in 0 until n) {
                    if ((b[i] and mask) != 0L) cnt++
                }
                if (cnt >= t) l1.add(mask)
            }
        } else {
            cs.clear()
            for (u in 0 until l0.size - 1) {
                for (v in u + 1 until l0.size) {
                    val w = l0[u] or l0[v]
                    if (w.countOneBits() == bs) {
                        cs[w] = cs[w] + 1
                    }
                }
            }
            val expect = bs * (bs - 1) / 2
            cs.forEach { w, oo ->
                if (oo != expect) return@forEach
                var cnt = 0
                for (i in 0 until n) {
                    if ((b[i] and w) == w) cnt++
                }
                if (cnt >= t) l1.add(w)
            }
        }
        if (l1.isEmpty()) break
        l1 = l0.also { l0 = l1 }
        l1.clear()
        bs++
    }
    return l0.first()
}

private val MAGIC = ((((sqrt(5.0) - 1) / 2) * 2.0.pow(63)).toLong() shl 1) or 1L
private val EMPTY_KEYS = LongArray(0)
private val EMPTY_VALS = IntArray(0)

class LLHashMap() {
    var size = 0
    var fill = 0
    var capacity: Int = 0
    var shift = 0
    var ks = EMPTY_KEYS
    var vs = EMPTY_VALS

    init { updateCapacity(4) }

    private fun updateCapacity(capacity: Int) {
        this.capacity = capacity
        shift = Integer.numberOfLeadingZeros(capacity) + 33
        ks = LongArray(capacity) { -1 }
        vs = IntArray(capacity)
    }

    fun clear() {
        ks.fill(-1L)
        vs.fill(0)
        fill = 0
        size = 0
    }

    private fun rehash(capacity: Int) {
        val oldKs = ks
        val oldVs = vs
        updateCapacity(capacity)
        for ((i0, k) in oldKs.withIndex()) {
            if (k < 0) continue
            val i = index(k)
            ks[i] = k
            vs[i] = oldVs[i0]
        }
        fill = size
    }

    private fun index(k: Long): Int {
        check(k >= 0)
        var i = ((k * MAGIC) ushr shift).toInt()
        while (ks[i] != k && ks[i] != -1L) {
            if (i == 0) i = capacity
            i--
        }
        return i
    }

    operator fun get(k: Long): Int = vs[index(k)]

    operator fun set(k: Long, v: Int) {
        if (fill >= capacity / 2) rehash(maxOf(4, Integer.highestOneBit(size) * 4))
        val i = index(k)
        if (ks[i] < 0) {
            ks[i] = k
            size++
            fill++
        }
        vs[i] = v
    }

    inline fun forEach(action: (Long, Int) -> Unit) {
        for ((i, k) in ks.withIndex()) {
            if (k < 0) continue
            action(k, vs[i])
        }
    }
}
