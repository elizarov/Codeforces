fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val s = n.toString()
        when (s.length) {
            2 -> println(s[1])
            else -> println(s.minOrNull()!!)
        }
    }
}