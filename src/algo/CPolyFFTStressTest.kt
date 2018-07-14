package algo

import java.util.*
import kotlin.math.*

fun main(args: Array<String>) {
    val rnd = Random(1)
    for (n in generateSequence(2) { it * 2 }) {
        val p = CPoly(n)
        var diff = 0.0
        repeat(10) {
            for (i in 0 until 2 * n) {
                p.a[i] = rnd.nextDouble()
            }
            val q = p.copy()
            q.fft()
            q.ifft()
            for (i in 0 until 2 * n) {
                diff = max(diff, q.a[i] - p.a[i])
            }
        }
        println("n=$n: diff=$diff")
    }
}