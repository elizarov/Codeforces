fun main() {
    data class S(val i: Int, val l: Int, val r: Int, var next: Int) : Comparable<S> {
        override fun compareTo(other: S): Int {
            if (l < other.l) return -1
            if (l > other.l) return 1
            return other.r - r
        }
    }
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = Array(m) { i ->
            val (l, r) = readln().split(" ").map { it.toInt() }
            S(i, l, r, m)
        }.sorted()
        val stack = ArrayList<S>()
        stack.add(S(-1, 1, n, m))
        for (i in 0..<m) {
            val top = stack.last()
            if (a[i].r > top.r) {
                top.next = i
                stack.removeLast()
            }
            stack += a[i]
        }
        val e = Array(n) { 1.toModInt() }
        for (i in 2..<n) {
            e[i] = e[i - 1] * 2
        }
        fun count(l: Int, r: Int, i: Int, j: Int): ModInt {
            if (i == j) return e[r - l]
            var ans = 1.toModInt()
            var prev = l
            var cur = a[i]
            while (true) {
                if (cur.l > prev) ans *= e[cur.l - prev - 1]
                ans *= count(cur.l, cur.r, cur.i + 1, cur.next)
                prev = cur.r + 1
                if (cur.next >= j) break
                cur = a[cur.next]
            }
            if (r > prev) ans *= e[r - prev]
            return ans
        }
        println(count(1, n, 0, m))
    }
}

private const val MOD = 998244353

@JvmInline value class ModInt(val x: Int) {
    init { require(x in 0 until MOD) }
    override fun toString(): String = x.toString()
}

fun Int.toModInt(): ModInt = ModInt(this.mod(MOD))

operator fun ModInt.plus(that: ModInt): ModInt = ModInt((x + that.x) % MOD)
operator fun ModInt.times(that: ModInt): ModInt = ModInt(((x.toLong() * that.x) % MOD).toInt())

operator fun ModInt.plus(that: Int): ModInt = ModInt((x + that).mod(MOD))
operator fun ModInt.times(that: Int): ModInt = this * that.toModInt()
