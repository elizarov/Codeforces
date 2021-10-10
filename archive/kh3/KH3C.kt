package archive.kh3

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val s = a.withIndex().sortedByDescending { it.value }
        val i = s.indexOfLast { it.value > 0 }
        val j = s.indexOfFirst { it.value < 0 }
        val c = CharArray(n) { '0' }
        for (k in 0 until i) c[s[k].index] = '1'
        if (j >= 0 && -s[j].value < s[i].value) {
            c[s[i].index] = '1'
            c[s[j].index] = '1'
        }
        val sum = s.sumBy { if (c[it.index] == '1') it.value else 0 }
        println(sum)
        println(c.joinToString(""))
    }
}
