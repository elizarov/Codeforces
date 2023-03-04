fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        if (a[0] == 1) a[0]++
        for (i in 1 until n) {
            while (a[i] % a[i - 1] == 0 || a[i] == 1) a[i]++
        }
        println(a.joinToString(" "))
    }
}