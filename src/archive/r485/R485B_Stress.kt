import java.util.*

fun main(args: Array<String>) {
    val n = 1000
    val k = 3 * n
    val rnd = Random()
    repeat(30) {
        val a = IntArray(n) { it + 1 }
        repeat(k) {
            val i = rnd.nextInt(n)
            val j0 = rnd.nextInt(n - 1)
            val j = if (j0 < i) j0 else j0 + 1
            val t = a[i]
            a[i] = a[j]
            a[j] = t
        }
        println(solveTr(n, a))
    }
}