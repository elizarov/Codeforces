private const val inf = 30

fun main() {
    val (ns, xs) = readln().split(" ")
    val n = ns.toInt()
    val x0 = xs.toLong()
    val dp = Array(inf + 1) { HashMap<Long,Int>() }
    fun find(x: Long, beat: Int): Int {
        dp[beat][x]?.let { return it }
        var dd = 0
        var rem = x
        var len = 0
        while (rem != 0L) {
            dd = dd or (1 shl ((rem % 10).toInt()))
            rem /= 10
            len++
        }
        var res: Int
        val left = n - len
        when {
            left <= 0 -> res = 0
            left > beat -> res = inf
            else -> {
                res = beat
                for (d in 9 downTo 2) if (dd and (1 shl d) != 0) {
                    res = minOf(res, find(x * d, res - 1) + 1)
                }
            }
        }
        dp[beat][x] = res
        return res
    }
    val res = find(x0, inf)
    if (res >= inf) {
        println(-1)
    } else {
        println(res)
    }
}