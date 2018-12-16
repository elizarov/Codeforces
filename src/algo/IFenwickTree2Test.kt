package algo

import org.junit.*
import java.util.*
import kotlin.test.*

class IFenwickTree2Test {
    @Test
    fun testRnd() {
        val n = 2
        val m = 2
        val a = Array(n) { IntArray(m) }
        val rnd = Random(1)
        val f = IFenwickTree2(n, m)
        repeat(1000) {
            val x = rnd.nextInt(n)
            val y = rnd.nextInt(m)
            val d = rnd.nextInt(100)
            a[x][y] += d
            f.update(x, y, d)
            val x1 = rnd.nextInt(n)
            val x2 = rnd.nextInt(n - x1) + x1
            val y1 = rnd.nextInt(m)
            val y2 = rnd.nextInt(m - y1) + y1
            var expect = 0
            for (i in x1..x2) for (j in y1..y2) expect += a[i][j]
            val actual = f.sum(x1, y1, x2, y2)
            assertEquals(expect, actual)
        }
    }
}