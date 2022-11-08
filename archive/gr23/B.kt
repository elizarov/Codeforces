fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var c = 0
        var i = 0
        var j = n - 1
        while (i < j) {
            while (i < j && a[i] == 0) i++
            while (i < j && a[j] == 1) j--
            if (i >= j) break
            c++
            i++
            j--
        }
        println(c)
    }
}