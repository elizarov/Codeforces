fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val p = IntArray(n) { i -> i + 1 }
        for (j in n - 2 downTo 0 step 2) {
            p[j] = p[j + 1].also { p[j + 1] = p[j] }
        }
        println(p.joinToString(" "))
    }
}