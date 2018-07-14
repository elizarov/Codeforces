package r482a

fun main(args: Array<String>) {
    val n = readLine()!!.toLong()
    val k = n + 1
    val r = when {
        k == 1L -> 0L
        k % 2 == 0L -> k / 2
        else -> k
    }
    println(r)
}