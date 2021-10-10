fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        var p = -1
        var c = 0
        for (x in a) {
            if (x < p) continue
            if (x == p) {
                p++
                c++
                continue
            }
            p = x
            c++
        }
        println(c)
    }
}