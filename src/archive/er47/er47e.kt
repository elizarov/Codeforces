package archive.er47

const val MOD = 998244353L

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.splitToIntArray()
    var sum = a[n - 1]
    var pow = 1
    for (i in n - 2 downTo 0) {
        sum = add(sum, mul(mul(a[i], pow), n - i + 1))
        pow = mul(pow, 2)
    }
    println(sum)
}

fun add(x: Int, y: Int) = ((x.toLong() + y) % MOD).toInt()
fun mul(x: Int, y: Int) = ((x.toLong() * y) % MOD).toInt()

private fun String.splitToIntArray(): IntArray {
    val n = length
    if (n == 0) return IntArray(0) // EMPTY
    var res = IntArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0
        var neg = false
        var c = get(i) // expecting number, IOOB if there is no number
        if (c == '-') {
            neg = true
            i++
            c = get(i) // expecting number, IOOB if there is no number
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d >= 0 && d <= 9) { "Unexpected character '$c' at $i" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $i" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $i" }
            i++
            if (i >= n) break
            c = get(i)
            if (c == ' ') break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m++] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $i" } }
        if (i >= n) break
        i++
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}