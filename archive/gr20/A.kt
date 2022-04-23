fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        if (a.sumOf { it - 1 } % 2 == 0) {
            println("maomao90")
        } else {
            println("errorgorn")
        }
    }
}