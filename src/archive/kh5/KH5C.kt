package archive.kh5

fun main() {
    repeat(readLine()!!.toInt()) {
        val s = readLine()!!
        val answer = s.count { it == 'w' } + s.split("w").sumOf { it.length / 2 }
        println(answer)
    }
}