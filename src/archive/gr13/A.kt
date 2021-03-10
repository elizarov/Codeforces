package archive.gr13

fun main() {
    val (n, q) = readLine()!!.split(" ").map { it.toInt() }
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    var c1 = a.count { it == 1 }
    val ans = ArrayList<Int>()
    repeat(q) {
        val (t, i) = readLine()!!.split(" ").map { it.toInt() }
        when (t) {
            1 -> {
                val old = a[i - 1]
                a[i - 1] = 1 - old
                when (old) {
                    0 -> c1++
                    1 -> c1--
                }
            }
            2 -> {
                ans.add(if (i > c1) 0 else 1)
            }
        }
    }
    println(ans.joinToString("\n"))
}