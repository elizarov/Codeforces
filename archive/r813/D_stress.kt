import kotlin.random.*

//@OptIn(ExperimentalTime::class)
fun main() {
//    val a = intArrayOf(5, 6, 4, inf, 4, 7, 7)
//    println(computeD(a))
//    val n = 100_000
//    val a = IntArray(n) { inf - it }
//    val time = measureTime { solveD(n, 1, a) }
//    println("${time.inWholeMilliseconds} ms")
    val n = 3
    while (true) {
        val a = IntArray(n) { Random.nextInt(1..10) }
        val k = Random.nextInt(1 until n)
        val ans = solveD(n, k, a.copyOf())
        val (ansS, maskS) = solveDStress(k, a.copyOf())
        if (ans != ansS) {
            println("$n $k")
            println(a.joinToString(" "))
            println("ans != ansS: $ans != $ansS (${maskS.toString(2)})")
            return
        }
    }
}

/*

fun solveDSimple(k: Int, a: IntArray): Int {
    val b = a.withIndex().sortedBy { it.value }.take(k).map { it.index }
    for (i in b) a[i] = inf
    val ans = computeD(a)
    return ans
}
*/

fun solveDStress(k: Int, a: IntArray): Pair<Int, Int> {
    val n = a.size
    var ans = 0
    var mask = 0
    for (m in 0 until (1 shl n)) if (m.countOneBits() == k) {
        val a1 = a.copyOf()
        for (i in 0 until n) if ((m and (1 shl i)) != 0) a1[i] = inf
        val c = computeD(a1)
        if (c > ans) {
            ans = c
            mask = m
        }
    }
    return ans to mask
}
