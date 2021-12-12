fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        var x = 1
        for (i in 0 until n) {
            if (a[i] == 1) {
                x += if (i > 0 && a[i - 1] == 1) 5 else 1
            } else {
                if (i > 0 && a[i - 1] == 0) {
                    x = -1
                    break
                }
            }
        }
        println(x)
    }
}