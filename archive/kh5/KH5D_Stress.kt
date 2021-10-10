package archive.kh5

fun main() {
    val n = 5
    val m = 3
    while (true) {
        val a = List(n) { (1..m).random() }
        val ans1 = solve(a)
        val c1 = count(a, ans1)
        val ans2 = solveFull(a)
        val c2 = count(a, ans2)
        if (c1 != c2) {
            println(n)
            println(a.joinToString(" "))
            println(ans1.joinToString(" ") + " | ${tr(a, ans1)} -> $c1")
            println(ans2.joinToString(" ") + " | ${tr(a, ans2)} -> $c2")
            return
        }
    }
}

private fun solve(_a: List<Int>): List<Int> {
    val a = _a.withIndex().sortedBy { it.value }
    var i = 0
    var j = a.lastIndex
    val answer = ArrayList<Int>(a.size)
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
    return answer

}

private fun count(a: List<Int>, answer: List<Int>): Int {
    var sum = 0
    var cur = 0
    for (i in answer) {
        if (cur >= a[i]) {
            sum++
            cur = 0
        }
        cur++
    }
    return sum
}

private fun tr(a: List<Int>, answer: List<Int>): String =
    answer.map { i -> a[i] }.joinToString(" ")


private fun solveFull(a: List<Int>): List<Int> {
    val answer = MutableList(a.size) { -1 }
    val u = BooleanArray(a.size)
    var best = -1
    var ba: List<Int>? = null
    fun find(i: Int) {
        if (i >= a.size) {
            val c = count(a, answer)
            if (c > best) {
                best = c
                ba = answer.toList()
            }
            return
        }
        for (j in a.indices) if (!u[j]) {
            u[j] = true
            answer[i] = j
            find(i + 1)
            u[j] = false
        }
    }
    find(0)
    return ba!!
}