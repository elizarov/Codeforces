fun main() {
    fun ordChoose(n: Int, k: Int): ModInt {
        var c = 1.toModInt()
        for (i in n - k + 1..n) c *= i
        return c
    }
    val n0 = readln().toInt()
    val m0 = 1 shl n0
    val mul = Array(n0 + 1) { ModIntArray(m0 + 1) }
    for (n in 2..n0) for (k in 1..m0) {
        val m1 = 1 shl (n - 1)
        mul[n][k] = ordChoose(m0 - k - m1, m1 - 1) * m1 * 2
    }
    fun calc(n: Int, k: Int, t: Int): ModInt {
        if (n == 1) return if (k >= t) 0.toModInt() else 2.toModInt()
        var sum = 0.toModInt()
        for (l in k + 1..t - 1) {
            sum += calc(n - 1, l, t)
        }
        //val m1 = 1 shl (n - 1)
        sum *= mul[n][k] // ordChoose(m0 - k - m1, m1 - 1) * m1 * 2
        return sum
    }
    val a = Array(n0 + 1) { ModIntArray(m0 + 1) }
    val ans = (1..m0).map { t ->
//        if (t == 2) for (k in 1..m0) a[1][k] = 2.toModInt()
        a[1][t - 1] = 2.toModInt()
        for (n in 2..n0) for (k in 1..t - 2) {
            // k + 1 <= t - 1
            // k <= t - 2
            a[n][k] += a[n - 1][t - 1] * mul[n][k]
        }
        //calc(n0, 1, t)
        a[n0][1]
    }
    println(ans.joinToString("\n"))
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

@JvmInline value class ModIntArray(private val a: IntArray) {
    operator fun get(index: Int): ModInt = ModInt(a[index])
    operator fun set(index: Int, value: ModInt) {
        a[index] = value.x
    }
    operator fun set(index: Int, value: Int) {
        a[index] = value.mod(MOD)
    }
}

fun ModIntArray(n: Int): ModIntArray = ModIntArray(IntArray(n))
