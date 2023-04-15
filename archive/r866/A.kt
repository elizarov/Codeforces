fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        println(if (solveA(a)) "Yes" else "No")
    }
}

fun mex(g: IntHashMap<G>): Int {
    var m = 0
    while ((g[m]?.c ?: 0) > 0) m++
    return m
}

fun solveA(a: IntArray): Boolean {
    val g = IntHashMap<G>()
    for ((i, x) in a.withIndex()) {
        val gx = g[x]
        if (gx == null) {
            g[x] = G(i, i, 1)
        } else {
            gx.c++
            gx.r = i
        }
    }
    val m = mex(g)
    val m1g = g[m + 1]
    if (m1g != null) {
        g[m] = G(m1g.l, m1g.r, 1)
        for (i in m1g.l..m1g.r) {
            g[a[i]]!!.c--
        }
        return mex(g) == m + 1
    }
    for (x in 0 until m) if (g[x]!!.c > 1) return true
    return a.any { it > m }
}

data class G(var l: Int, var r: Int, var c: Int)

private const val MAGIC = 0x9E3779B9.toInt() // phi
private const val LIMIT = 0xAAAAAAAA.toInt() // 2/3
private val EMPTY_KEYS = IntArray(0)
private val EMPTY_VALS = arrayOfNulls<Any?>(0)

@Suppress("UNCHECKED_CAST")
class IntHashMap<V>() {
    var size = 0
    var fill = 0
    var capacity: Int = 0
    var shift = 0
    var ks = EMPTY_KEYS
    var vs = EMPTY_VALS

    init { updateCapacity(4) }

    private fun updateCapacity(capacity: Int) {
        this.capacity = capacity
        shift = Integer.numberOfLeadingZeros(capacity) + 1
        ks = IntArray(capacity) { -1 }
        vs = arrayOfNulls<Any?>(capacity)
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

    private fun index(k: Int): Int {
        check(k >= 0)
        var i = (k * MAGIC) ushr shift
        while (ks[i] != k && ks[i] != -1) {
            if (i == 0) i = capacity
            i--
        }
        return i
    }

    operator fun get(k: Int): V? = vs[index(k)] as V?

    operator fun set(k: Int, v: V) {
        if (fill >= LIMIT ushr shift) rehash(maxOf(4, Integer.highestOneBit(size) * 4))
        val i = index(k)
        if (ks[i] < 0) {
            ks[i] = k
            size++
            fill++
        }
        vs[i] = v
    }

    fun remove(k: Int): V? {
        val i = index(k)
        if (ks[i] < 0) return null
        ks[i] = -2
        size--
        return (vs[i] as V).also { vs[i] = null }
    }

    inline fun forEach(action: (Int, V) -> Unit) {
        for ((i, k) in ks.withIndex()) {
            if (k < 0) continue
            action(k, vs[i] as V)
        }
    }
}