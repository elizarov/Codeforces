package algo

import org.junit.*
import java.util.*
import kotlin.test.*

class ISegmentTreeTest {
    @Test
    fun testSum() {
        for (n in 1..100) {
            testSum(n)
        }
    }

    private fun testSum(n: Int) {
        val rnd = Random(1)
        val a = IntArray(n) { rnd.nextInt(1000) }
        val t = ISegmentTree(a, 0) { x, y -> x + y }
        repeat(100) {
            val l = rnd.nextInt(n)
            val r = rnd.nextInt(n)
            if (l <= r) {
                val ta = t[l, r]
                val ea = a.sliceArray(l..r).sum()
                assertEquals(ea, ta)
            }
            val i = rnd.nextInt(n)
            val x = rnd.nextInt(1000)
            a[i] = x
            t[i] = x
        }
    }
}