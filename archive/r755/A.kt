fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val b = readLine()!!.split(" ").map { it.toInt() }
        println(if (solveA(a, b)) "YES" else "NO")
    }
}

fun solveA(a: List<Int>, b: List<Int>): Boolean {
    val ac = a.groupingBy { it }.eachCount()
    val bc = b.groupingBy { it }.eachCount()
    var used = 0
    var prev = Int.MAX_VALUE
    for (i in (a + b).distinct().sortedDescending()) {
        if (used > 0 && prev != i + 1) return false
        val ar = (ac[i] ?: 0) - used
        val br = bc[i] ?: 0
        if (ar < 0) return false
        if (ar > br) return false
        // ar <= br
        used = br - ar
        prev = i
    }
    return used == 0
}
