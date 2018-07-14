package r487

fun main(args: Array<String>) {
    val (n, p) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!.toCharArray()
    var success = false
    for (i in 0 until p) {
        var dc = 0
        var p0 = false
        var p1 = false
        for (j in i until n step p) {
            when(s[j]) {
                '.' -> dc++
                '0' -> p0 = true
                '1' -> p1 = true
                else -> error("!")
            }
        }
        var start = '0'
        when {
            dc >= 2 || p0 && p1 -> success = true
            dc >= 1 -> {
                if (p0) {
                    success = true
                    start = '1'
                }
                if (p1) {
                    success = true
                    start = '0'
                }
            }
        }
        for (j in i until n step p) {
            if (s[j] == '.') {
                s[j] = start
                start = ('0'.toInt() + '1'.toInt() - s[j].toInt()).toChar()
            }
        }
    }
    if (success) {
        println(String(s))
    } else {
        println("No")
    }
}