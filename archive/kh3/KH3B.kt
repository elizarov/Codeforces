package archive.kh3

fun main() {
    class M(val a: Int, val b: Int)
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val ms = Array(n) {
            readLine()!!.split(" ").map { it.toInt() }.let { (a, b) -> M(a, b) }
        }
        var ans = -1
        fun check(x: Int) {
            if (ms.count { m -> x in m.a..m.b } == 1) ans = x
        }
        for (m in ms) {
            for (d in -1..1) {
                check(m.a + d)
                check(m.b + d)
            }
        }
        println(ans)
    }
}