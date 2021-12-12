fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        var k = 0
        var i = n - 1
        while (true) {
            val cur = a[i]
            i--
            while (i >= 0 && a[i] <= cur) i--
            if (i < 0) break
            k++
        }
        println(k)
    }
}