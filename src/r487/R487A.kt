package r487

fun main(args: Array<String>) {
    val s = readLine()!!
    val n = s.length
    for (i in 1..n - 2  ) {
        val ss = setOf(s[i - 1], s[i], s[i + 1])
        if (!ss.contains('.') && ss.size == 3) {
            println("Yes")
            return
        }
    }
    println("No")
}