fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val c = a.groupingBy { it }.eachCount().values.sorted()
        val m = IntArray(n + 1)
        for (k in 1..n) {

        }
        
    }
}