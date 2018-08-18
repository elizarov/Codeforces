package archive.r491

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split( " ").map { it.toInt() }
    val c = IntArray(6)
    for (i in a) c[i]++
    var k = 2
    var res = 0
    var s = a.sum()
    while (10 * s < 45 * n) {
        while (c[k] == 0) k++
        s += 5 - k
        c[k]--
        res++
    }
    println(res)
}