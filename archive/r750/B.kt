fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val c0 = a.count { it == 0 }
        val c1 = a.count { it == 1 }
        println((1L shl c0) * c1)
    }
}