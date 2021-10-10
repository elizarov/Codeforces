package archive.er89

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, x , m) = readLine()!!.split(" ").map { it.toInt() }
        var a = x
        var b = x
        repeat(m) {
            val (l, r) = readLine()!!.split(" ").map { it.toInt() }
            if (maxOf(l, a) <= minOf(r, b)) {
                val a1 = minOf(l, a)
                val b1 = maxOf(r, b)
                a = a1
                b = b1
            }
        }
        println(b - a + 1)
    }
}