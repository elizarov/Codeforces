fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val g = Array(n) { readln() }
        fun order(list: List<Int>): List<Int> {
            if (list.size <= 1) return list
            val x = list[0]
            val (left, right) = list.drop(1).partition { i -> g[x - 1][i - 1] == '0' }
            return order(left) + x + order(right)
        }
        println(order((1..n).toList()).joinToString(" "))
    }
}