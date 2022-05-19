fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val s = readLine()!!
        val t = s.toCharArray().sortedArray().concatToString()
        if (s == t) {
            println(0)
        } else {
            val a = (0 until n).filter { i -> s[i] != t[i] }.map { it + 1 }
            println(1)
            println("${a.size} " + a.joinToString(" "))
        }
    }
}