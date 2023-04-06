package algo

import org.junit.Test
import java.util.*
import kotlin.test.*

class LFenwickTreeTest {
    @Test
    fun testFillSum() {
        val n = 10_000
        val f = LFenwickTree(n)
        val v = 42L
        f.fill(v)
        val rnd = Random(1)
        repeat(100) {
            val b = rnd.nextInt(n)
            val a = rnd.nextInt(b + 1)
            val e = (b - a + 1) * v
            assertEquals(e, f.sum(a, b))
        }
    }

    @Test
    fun testUpdate() {
        val n = 1000
        val f = LFenwickTree(n)
        val v = MutableList(n) { 0L }
        val rnd = Random(1)
        repeat(1000) {
            val i = rnd.nextInt(n)
            val d = rnd.nextLong()
            v[i] += d
            f.update(i, d)

            val b = rnd.nextInt(n)
            val a = rnd.nextInt(b + 1)
            val e = v.subList(a, b + 1).sum()
            assertEquals(e, f.sum(a, b))
        }
    }
}