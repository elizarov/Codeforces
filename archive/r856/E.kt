fun main() {
    val n = readln().toInt()
    val a = readln().split(" ").map { it.toInt() }
    val g = Graph(n, 2 * n - 2)
    repeat(n - 1) {
        val (u, v) = readln().split(" ").map { it.toInt() - 1 }
        g.add(u, v)
        g.add(v, u)
    }
    val h = computeHash(n, g).a.withIndex().groupBy { it.value }
    val ans = HashSet<Int>()
    val c = a.groupingBy { it }.eachCount()
    var h0 = 0.toModPair()
    var missing = 0
    val m = c.keys.max()
    for (i in m downTo 0) {
        val ci0 = c[i]
        val ci = if (ci0 == null) {
            missing++
            if (missing > 1) break
            1.toModPair()
        } else ci0.toModPair()
        h0 = ci + h0 * BASE
    }
    when (missing) {
        1 -> h[h0.x]?.let { list -> ans += list.map { it.index } }
        0 -> {
            var h1 = 1.toModPair()
            for (i in 1..m + 1) {
                h1 *= BASE
                h[(h0 + h1).x]?.let { list -> ans += list.map { it.index } }
            }
        }
    }
    println(ans.size)
    println(ans.sorted().map { it + 1 }.joinToString(" "))
}

private fun computeHash(n: Int, g: Graph): ModPairArray {
    val down = ModPairArray(n)
    val f = BooleanArray(n)
    val dfs1 = DeepRecursiveFunction<Int, ModPair> { v ->
        f[v] = true
        var res = 1.toModPair()
        g.from(v) { u ->
            if (!f[u]) {
                res += BASE * callRecursive(u)
            }
        }
        down[v] = res
        res
    }
    dfs1(0)
    f.fill(false)
    val hash = ModPairArray(n)
    data class P(val v: Int, val a: ModPair)
    val dfs2 = DeepRecursiveFunction<P, Unit> { (v, a) ->
        f[v] = true
        hash[v] = down[v] + a
        g.from(v) { u ->
            if (!f[u]) {
                val b = (a + down[v] - BASE * down[u]) * BASE
                callRecursive(P(u, b))
            }
        }
    }
    dfs2(P(0, 0.toModPair()))
    return hash
}

class Graph(vCap: Int = 16, eCap: Int = vCap * 2) {
    var eCnt = 0
    var vHead = IntArray(vCap) { -1 }
    var eVert = IntArray(eCap)
    var eNext = IntArray(eCap)

    fun add(v: Int, u: Int, e: Int = eCnt++) {
        eVert[e] = u
        eNext[e] = vHead[v]
        vHead[v] = e
    }

    inline fun from(v: Int, action: (u: Int) -> Unit) {
        var e = vHead[v]
        while (e >= 0) {
            action(eVert[e])
            e = eNext[e]
        }
    }
}

private val BASE = ModPair(200903, 201247)

private const val MOD = 1000000007

fun ModPair(x1: Int, x2: Int) = ModPair(x1 + x2.toLong().shl(32))

@JvmInline value class ModPair(val x: Long) {
    init { require(x.toInt() in 0 until MOD && x.shr(32).toInt() in 0 until MOD) }
    val x1: Int get() = x.toInt()
    val x2: Int get() = x.shr(32).toInt()
    override fun toString(): String = "$x1,$x2"
}

fun Int.toModPair(): ModPair = this.mod(MOD).let { x -> ModPair(x, x) }

operator fun ModPair.plus(that: ModPair): ModPair = ModPair((x1 + that.x1) % MOD, (x2 + that.x2) % MOD)
operator fun ModPair.minus(that: ModPair): ModPair = ModPair((x1 - that.x1).mod(MOD), (x2 - that.x2).mod(MOD))
operator fun ModPair.times(that: ModPair): ModPair = ModPair(((x1.toLong() * that.x1) % MOD).toInt(), ((x2.toLong() * that.x2) % MOD).toInt())

@JvmInline value class ModPairArray(val a: LongArray) {
    operator fun get(index: Int): ModPair = ModPair(a[index])
    operator fun set(index: Int, value: ModPair) {
        a[index] = value.x
    }
}

fun ModPairArray(n: Int): ModPairArray = ModPairArray(LongArray(n))
