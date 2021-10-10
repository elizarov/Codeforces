package archive.er87

fun main() {
    repeat(readLine()!!.toInt()) {
        val s = readLine()!!
        println(solveB(s))
    }
}

fun solveB(s: String): Int {
    var res = Int.MAX_VALUE
    for (a in '1'..'3') {
        for (b in '1'..'3') if (b != a) {
            for (c in '1'..'3') if (c != a && c != b) {
                res = minOf(res, solveB2(s, a, b, c))
            }
        }
    }
    return res.takeIf { it < Int.MAX_VALUE } ?: 0
}

fun solveB2(s: String, a: Char, b: Char, c: Char): Int {
    var res = Int.MAX_VALUE
    var i = 0
    var j = 0
    var m = 0
    while (true) {
        while (i < s.length && s[i] != a) {
            if (s[i] == b) m--
            i++
        }
        if (i >= s.length) return res
        while (j < s.length && (s[j] != c || j <= i || m <= 0)) {
            if (s[j] == b) m++
            j++
        }
        if (j >= s.length) return res
        res = minOf(res, j - i + 1)
        i++
    }
}
