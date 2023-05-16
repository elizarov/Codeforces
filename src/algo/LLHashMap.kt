package algo

private const val MAGIC = 0x9E3779B9.toInt() // phi
private const val LIMIT = 0xAAAAAAAA.toInt() // 2/3
private val EMPTY_KEYS = LongArray(0)
private val EMPTY_VALS = EMPTY_KEYS

@Suppress("UNCHECKED_CAST")
class LLHashMap() {
    var size = 0
    var fill = 0
    var capacity = 0
    var shift = 0
    var ks = EMPTY_KEYS
    var vs = EMPTY_VALS

    init { updateCapacity(4) }

    private fun updateCapacity(capacity: Int) {
        this.capacity = capacity
        shift = Integer.numberOfLeadingZeros(capacity) + 1
        ks = LongArray(capacity) { -1 }
        vs = LongArray(capacity)
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

    private fun hash(k: Long): Int = (k shr 32).toInt() * 1_000_000_007 + k.toInt()

    private fun index(k: Long): Int {
        check(k >= 0)
        var i = (hash(k) * MAGIC) ushr shift
        while (ks[i] != k && ks[i] != -1L) {
            if (i == 0) i = capacity
            i--
        }
        return i
    }

    operator fun get(k: Long): Long = vs[index(k)]

    operator fun set(k: Long, v: Long) {
        if (fill >= LIMIT ushr shift) rehash(maxOf(4, Integer.highestOneBit(size) * 4))
        val i = index(k)
        if (ks[i] < 0) {
            ks[i] = k
            size++
            fill++
        }
        vs[i] = v
    }
}