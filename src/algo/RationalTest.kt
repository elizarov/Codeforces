package algo

import org.junit.*
import kotlin.test.*

class RationalTest {
    @Test
    fun testFromWiki() {
        val q = Q(84375, 100000)
        assertEquals(listOf(0L, 1L, 5L, 2L, 2L), q.continuousFraction().toList())
        assertEquals(Q(1, 1), q.limitDenominator(1))
        assertEquals(Q(5, 6), q.limitDenominator(10))
        assertEquals(Q(16, 19), q.limitDenominator(20))
    }

    @Test
    fun testPrecision() {
        assertEquals(Q(1, 1), Q(
            257309487696720955,
            312684215973201768
        ).limitDenominator(1))
        assertEquals(Q(7, 12), Q(
            578008605971435323,
            989617788455724096
        ).limitDenominator(12))
    }
}