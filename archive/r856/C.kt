fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val c = IntArray(n)
        var j = 0
        for (i in 0 until n) {
            while (a[j] < i - j + 1) j++
            c[i] = i - j + 1
        }
        println(c.joinToString(" "))
        
    }
}