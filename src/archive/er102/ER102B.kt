fun main() {
    repeat(readLine()!!.toInt()) {
        val s = readLine()!!
        val t = readLine()!!
        val sp = s.repeat(t.length)
        val tp = t.repeat(s.length)
        if (sp != tp) {
            println(-1)
        } else {
            println(s.repeat(t.length / gcd(s.length, t.length)))
        }
    }
}

private tailrec fun gcd(x: Int, y: Int): Int = if (x == 0) y else gcd(y % x, x)
