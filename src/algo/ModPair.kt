package algo

// Safe polynomial hash computation with unlikely collisions
// Using a single good modulo with two different bases

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
