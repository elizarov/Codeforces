import java.util.PriorityQueue

private const val mod = 1_000_000_007

data class Num(val k: Int, val p: Int, val p2Mod: Int = (1 shl p) % mod) : Comparable<Num> {
    val resMod = ((k.toLong() * p2Mod) % mod).toInt()

    override fun compareTo(other: Num): Int = k.compareTo(other.k)
    fun mul2Pow(p: Int, p2Mod: Int): Num = Num(k, this.p + p, ((this.p2Mod.toLong() * p2Mod) % mod).toInt())
    fun improvesTo(other: Num): Boolean {
        val bits = 32 - other.k.countLeadingZeroBits() + other.p
        if (bits > 31) return true
        val b = other.k shl other.p
        return k < b
    }
}

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var sum = 0
        val even = PriorityQueue<Num>()
        val ans = IntArray(n) { i ->
            val p0 = a[i].countTrailingZeroBits()
            val k0 = a[i] shr p0
            var num = Num(k0, p0)
            while (even.isNotEmpty() && even.first().improvesTo(num)) {
                val prev = even.remove()
                sum = (sum + mod - prev.resMod) % mod
                sum = (sum + prev.k) % mod
                num = num.mul2Pow(prev.p, prev.p2Mod)
            }
            sum = (sum + num.resMod) % mod
            if (num.p > 0) even.add(num)
            sum
        }
        println(ans.joinToString(" "))
    }
}