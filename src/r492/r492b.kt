package r492

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    var sum = 0
    for (i in 0 until a.size step 2) {
        val c = a[i]
        var j = i + 1
        while (a[j] != c) j++
        val len = j - i - 1
        sum += len
        System.arraycopy(a, i + 1, a, i + 2, len)
    }
    println(sum)
}