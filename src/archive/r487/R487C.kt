package archive.r487

fun main(args: Array<String>) {
    val (a, b, c, d) = readLine()!!.split(" ").map { it.toInt() }
    val n = 50
    val f = Array(n) { CharArray(n) { 'A' } }
    var i = 1
    var p = 0
    repeat(b) {
        f[i][p + (i % 2) + 1] = 'B'
        i++
        if (i == n - 1) {
            i = 1
            p += 3
        }
    }
    repeat(c) {
        f[i][p + (i % 2) + 1] = 'C'
        i++
        if (i == n - 1) {
            i = 1
            p += 3
        }
    }
    repeat(d - 1) {
        f[i][p + (i % 2) + 1] = 'D'
        i++
        if (i == n - 1) {
            i = 1
            p += 3
        }
    }
    p += 4
    for (j in 0 until n) {
        f[j].fill('D', p, n)
    }
    i = 1
    repeat(a - 1) {
        f[i][p + (i % 2) + 1] = 'A'
        i++
        if (i == n - 1) {
            i = 1
            p += 3
        }
    }
    println("$n $n")
    f.forEach { println(String(it)) }
}