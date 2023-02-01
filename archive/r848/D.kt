fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln()
        val b = readln()
        val p0 = (0 until n).count { i -> a[i] == b[i] }
        val x = ModIntArray(n + 1)
        val y = ModIntArray(n + 1)
        // e[p] = x[i] + y[i] * e[0]
        x[0] = 0; y[0] = 1
        x[1] = -1; y[1] = 1
        for (p in 1 until n) {
            // e[p + 1] = n/(n-p) * e[p] - p/(n-p) * e[p - 1] - n/(n-p)
            //            ------           ------               -------
            //               f0              f1                    f0
            val div = (n - p).modInv()
            val f0 = div * n
            val f1 = div * p
            x[p + 1] = (f0 * x[p]) - (f1 * x[p - 1]) - f0
            y[p + 1] = (f0 * y[p]) - (f1 * y[p - 1])
        }
        // e[n] = 0 => e[0] = -x[n] / y[n]
        val e0 = -x[n] * y[n]
        val ep = x[p0] + (y[p0] * e0)
        println(ep)
    }
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
