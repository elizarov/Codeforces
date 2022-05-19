fun main() {
    val (n, q) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!.toCharArray()
    fun abc(i: Int) = i in 0..n - 3 && s[i] == 'a' && s[i + 1] == 'b' && s[i + 2] == 'c'
    var cnt = (0 until n).count { abc(it) }
    repeat(q) {
        val qs = readLine()!!.split(" ")
        val i = qs[0].toInt() - 1
        val old = (i - 2..i).count { abc(it) }
        s[i] = qs[1][0]
        val new = (i - 2..i).count { abc(it) }
        cnt += new - old
        println(cnt)
    }
}