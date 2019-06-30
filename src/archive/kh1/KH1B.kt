package archive.kh1

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }
    var m1 = 0
    var m2 = 0
    var c = 0
    for (x in a) {
        if (m2 > x) {
            c++
        }
        when {
            x > m1 -> {
                m2 = m1
                m1 = x
            }
            x > m2 -> {
                m2 = x
            }
        }
    }
    println(c)
}