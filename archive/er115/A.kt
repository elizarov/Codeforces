fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = Array(2) { readLine()!! }
        var ok = true
        for (i in 1 until n) {
            if (a[0][i] == '1' && a[1][i] == '1') ok = false
        }
        println(if (ok) "YES" else "NO")
    }
}