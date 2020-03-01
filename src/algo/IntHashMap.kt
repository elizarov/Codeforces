package algo

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

fun main() {
    val h = IntHashMap<Int>()
    val n = 1000
    repeat(3) {
        for (i in 1..n) h[i] = i
        check(h.size == n)
        for (i in 1..n) check(h[i] == i)
        for (i in 1..n) check(h[i] == i)
        check(h.size == n)
        for (i in 1..n step 2) check(h.remove(i) == i)
        check(h.size == n / 2)
        for (i in 2..n step 2) check(h.remove(i) == i)
        check(h.size == 0)
    }
}