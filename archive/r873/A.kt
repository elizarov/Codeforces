fun main() {
    runA()
}

fun runA() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray().sorted()
        val b = readln().split(" ").map { it.toInt() }.toIntArray().sorted()
        var i = n
        var ans = 1.toModInt()
        for (j in n - 1 downTo 0) {
            while (i > 0 && a[i - 1] > b[j]) i--
            ans *= n - i - (n - 1 - j)
        }
        println(ans)
    }
}

private const val MOD = 1_000_000_007

@JvmInline value class ModInt(val x: Int) {
    init { require(x in 0 until MOD) }
    override fun toString(): String = x.toString()
}

fun Int.toModInt(): ModInt = ModInt(this.mod(MOD))
operator fun ModInt.times(that: ModInt): ModInt = ModInt(((x.toLong() * that.x) % MOD).toInt())
operator fun ModInt.times(that: Int): ModInt = this * that.toModInt()
