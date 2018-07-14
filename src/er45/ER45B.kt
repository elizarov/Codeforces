package er45b

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    println(solve(readLine()!!, n, k))
}

fun solve(aLine: String, n: Int, k: Int): Int {
    val a = aLine.split(" ").map { it.toInt() }.sorted().toIntArray()
    var i = 0
    var swc = 0
    for (j in 0 until n) {
        while (i < n && a[i] <= a[j]) i++
        if (i < n && a[i] <= a[j] + k) swc++
    }
    val res = n - swc
    return n - swc
}
