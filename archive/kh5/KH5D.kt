package archive.kh5

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
            .withIndex().sortedBy { it.value }
        var i = 0
        var j = n - 1
        val answer = ArrayList<Int>(n)
        var bc = 0
        while (i <= j) {
            if (bc >= a[i].value) {
                answer += a[i++].index
                bc = 1
            } else {
                answer += a[j--].index
                bc++
            }
        }
        println(answer.joinToString(" ") { (it + 1).toString() })
    }
}