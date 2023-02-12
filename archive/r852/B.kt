fun main() {
    repeat(readln().toInt()) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        val a = ArrayList<Int>()
        for (t in x downTo y) a += t
        for (t in y + 1..x - 1) a += t
        println(a.size)
        println(a.joinToString(" "))
    }
}