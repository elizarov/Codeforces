import kotlin.test.assertEquals

fun main() {
    val n = 127
    for (i in 1..n) {
        val r = BooleanArray(n + 1)
        r[i] = true
        for (u in i..n) {
            if (!r[u]) continue
            for (k in u + 1..n) {
                val v = k - u
                if ((u and v) == v) {
                    r[k] = true
                }
            }
        }
        for (j in 1..n) {
            assertEquals(r[j], solveD(i, j), "$i $j, expected: ${r[j]}")
        }
    }

}