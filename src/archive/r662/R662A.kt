package archive.r662

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        println(solveA(n))
    }
}

fun solveA(n: Int): Int = n / 2 + 1
