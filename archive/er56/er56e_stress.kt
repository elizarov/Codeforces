package archive.er56

import kotlin.system.*

fun main(args: Array<String>) {
    val n = 200000
    val m = 200000
    var pi: PermIntersect? = null
    val t1 = measureTimeMillis {
        val a = IntArray(n) { it + 1 }
        val b = IntArray(n) { it + 1 }
        pi = PermIntersect(n, a, b)
    }
    println("Build in $t1")
    val t2 = measureTimeMillis {
        repeat(m) {
            check(pi!!.doQuery(1, n - 2, 1, n - 2) == n - 2)
        }
    }
    println("Query in $t2")

}