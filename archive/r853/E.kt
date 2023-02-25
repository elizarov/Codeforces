fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val s = readln().split(" ").map { it.toInt() }
        val sn = s.last()
        var ans = sn.toModInt() * (sn + 1) / 2 * n
        val fl = ArrayList<Int>()
        var d = 2
        var rem = sn
        while (d * d <= rem) {
            while (rem % d == 0) {
                rem /= d
                fl += d
            }
            d++
        }
        if (rem > 1) fl += rem
        val fc = fl.groupingBy { it }.eachCount().toList()
        fun scan(i: Int, x: Int, sx: Int) {
            if (i >= fc.size) {
                for (j in 0 until n) if (s[j] % sx != 0) ans -= x
                return
            }
            scan(i + 1, x, sx)
            val (p, pc) = fc[i]
            var pw = 1
            for (c in 1..pc) {
                pw *= p
                scan(i + 1, x / pw, sx * pw)
            }
        }
        scan(0, sn, 1)
        println(ans)
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
