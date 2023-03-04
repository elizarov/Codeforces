fun main() {
    val n = readln().toInt()
    val a = readln().split(" ").map { it.toInt() }
    val m = a.max() + 1
    val p = BooleanArray(m) { true }
    p[1] = false
    for (i in 2 until m) {
        if (!p[i]) continue
        var j = 2 * i
        while (j < m) {
            p[j] = false
            j += i
        }
    }
    val (pc0, cc0) = a.groupingBy { it }.eachCount().toList().partition { p[it.first] }
    if (pc0.size < n) {
        println(0)
        return
    }
    val pc = pc0.map { it.second }.groupingBy { it }.eachCount().toList()
    val cc = cc0.map { it.second }.groupingBy { it }.eachCount().toList()
    val f = ModIntArray(2 * n + 1)
    f[0] = 1
    for (i in 1..2 * n) f[i] = f[i - 1] * i
    val inv = ModIntArray(2 * n + 1)
    for (i in 1..2 * n) inv[i] = i.modInv()
    val dp = Array(pc.size + 1) { IntArray(n + 1) { -1 } }
    fun compute(i: Int, rem: Int): ModInt {
        if (i >= pc.size) {
            if (rem == 0) return 1.toModInt()
            return 0.toModInt()
        }
        val res0 = dp[i][rem]
        if (res0 >= 0) return ModInt(res0)
        val (c, k) = pc[i]
        var res = 0.toModInt()
        var cnt = 1.toModInt()
        for (j in 0..minOf(k, rem)) {
            res += compute(i + 1, rem - j) * cnt
            cnt *= c
            cnt *= k - j
            cnt *= inv[j + 1]
        }
        dp[i][rem] = res.x
        return res
    }
    var cd = ModInt(1)
    for ((c, k) in (pc + cc)) {
        repeat(k) { cd *= f[c] }
    }
    println(compute(0, n) * f[n] / cd)
}

private const val MOD = 998244353

@JvmInline value class ModInt(val x: Int) {
    init { require(x in 0 until MOD) }
    override fun toString(): String = x.toString()
}

fun Int.toModInt(): ModInt = ModInt(this.mod(MOD))

operator fun ModInt.plus(that: ModInt): ModInt = ModInt((x + that.x) % MOD)
operator fun ModInt.minus(that: ModInt): ModInt = ModInt((x - that.x).mod(MOD))
operator fun ModInt.times(that: ModInt): ModInt = ModInt(((x.toLong() * that.x) % MOD).toInt())
operator fun ModInt.div(that: ModInt): ModInt = this * that.inv()
operator fun ModInt.unaryMinus(): ModInt = ModInt(if (x == 0) 0 else MOD - x)

operator fun ModInt.plus(that: Int): ModInt = ModInt((x + that).mod(MOD))
operator fun ModInt.minus(that: Int): ModInt = ModInt((x - that).mod(MOD))
operator fun ModInt.times(that: Int): ModInt = this * that.toModInt()
operator fun ModInt.div(that: Int): ModInt = this * that.toModInt().inv()

fun ModInt.inv(): ModInt {
    require(x != 0)
    var t = 0
    var r = MOD
    var newT = 1
    var newR = x
    while (newR != 0) {
        val q = r / newR
        t = newT.also { newT = t - q * newT }
        r = newR.also { newR = r - q * newR }
    }
    check(r == 1)
    if (t < 0) t += MOD
    return ModInt(t)
}

fun Int.modInv() = toModInt().inv()

@JvmInline value class ModIntArray(val a: IntArray) {
    operator fun get(index: Int): ModInt = ModInt(a[index])
    operator fun set(index: Int, value: ModInt) {
        a[index] = value.x
    }
    operator fun set(index: Int, value: Int) {
        a[index] = value.mod(MOD)
    }
}

fun ModIntArray(n: Int): ModIntArray = ModIntArray(IntArray(n))
