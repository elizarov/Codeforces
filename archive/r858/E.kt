data class Q(val x: Int, val y: Int)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }.toIntArray()
    val p = (listOf(-1) + readln().split(" ").map { it.toInt() - 1 }).toIntArray()
    val qs = Array(m) {
        val (x, y) = readln().split(" ").map { it.toInt() - 1 }
        Q(x, y)
    }
    val ans = solveE(a, p, qs)
    println(ans.joinToString("\n"))
}

fun qq(x: Int, y: Int): Long = (x.toLong() shl 32) + y

fun solveE(
    a: IntArray,
    p: IntArray,
    qs: Array<Q>
): LongArray {
    val dp = LLHashMap()
    dp[qq(0, 0)] = a[0].toLong() * a[0]
    val hx = IntArray(a.size)
    val hy = IntArray(a.size)
    val ans = LongArray(qs.size)
    for (t in 0 until qs.size) {
        val q0 = qs[t]
        var x = q0.x
        var y = q0.y
        var k = 0
        if (x > y) x = y.also { y = x }
        var r: Long
        while (true) {
            val r0 = dp[qq(x, y)]
            if (r0 != 0L) {
                r = r0
                break
            }
            hx[k] = x
            hy[k] = y
            k++
            x = p[x]
            y = p[y]
            if (x > y) x = y.also { y = x }
        }
        for (j in k - 1 downTo 0) {
            x = hx[j]
            y = hy[j]
            r += a[x].toLong() * a[y]
            if ((k - j) % 2 == 0) dp[qq(x, y)] = r
        }
        ans[t] = r
    }
    return ans
}

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