package archive.r505

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val s = readLine()!!.toCharArray()
    if (n == 1) {
        println("Yes")
        return
    }
    val c = IntArray(26)
    for (x in s) {
        c[x - 'a']++
    }
    for (y in c) {
        if (y >= 2) {
            println("Yes")
            return
        }
    }
    println("No")
}