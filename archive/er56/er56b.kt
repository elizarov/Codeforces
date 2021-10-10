package archive.er56

fun main(args: Array<String>) {
    val t = readLine()!!.toInt()
    repeat(t) {
        val s = readLine()!!.toCharArray()
        s.sort()
        if (s.first() == s.last()) {
            println(-1)
        } else {
            println(String(s))
        }
    }
}