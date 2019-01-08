package archive.goodbye2018

fun main() {
    val n = readLine()!!.toInt()
    val fac = IntArray(n + 1)
    fac[0] = 1
    for (i in 1..n) fac[i] = mul(fac[i - 1], i)
    val rem = IntArray(n + 2)
    rem[n + 1] = 1
    for (i in n downTo 1) rem[i] = mul(rem[i + 1], i)
    var sum = fac[n]
    for (i in 1..n) sum = add(
        sum,
        mul(rem[i], add(fac[i - 1], MOD - 1))
    )
    println(sum)
}

private const val MOD = 998244353

fun mul(a: Int, b: Int) = ((a.toLong() * b) % MOD).toInt()
fun add(a: Int, b: Int) = ((a.toLong() + b) % MOD).toInt()
