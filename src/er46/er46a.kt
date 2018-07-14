package er46

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val a = List(n) { readLine()!! }.groupingBy { it }.eachCount()
    val b = List(n) { readLine()!! }.groupingBy { it }.eachCount()
    var res = 0
    for ((s, cnt) in b) {
        val cur = a[s] ?: 0
        if (cnt > cur) res += cnt - cur
    }
    println(res)
}