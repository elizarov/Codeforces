package archive.r707

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }
    solveA(n, a)
}

fun solveA(n: Int, a: List<Int>) {
    val sl = arrayOfNulls<ArrayList<Int>>(5_000_001)
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val s = a[i] + a[j]
            val l = sl[s] ?: ArrayList<Int>().also { sl[s] = it }
            if (!l.contains(i)) l += i
            if (!l.contains(j)) l += j
            for (xi in 0 until l.size - 3) {
                val x = l[xi]
                for (yi in xi + 1 until l.size - 2) {
                    val y = l[yi]
                    for (zi in yi + 1 until l.size - 1) {
                        val z = l[zi]
                        for (wi in zi + 1 until l.size) {
                            val w = l[wi]
                            if (a[x] + a[y] == a[z] + a[w]) {
                                println("YES")
                                println("${x + 1} ${y + 1} ${z + 1} ${w + 1}")
                                return
                            }
                        }
                    }
                }
            }
        }
    }
    println("NO")
}