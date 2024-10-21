package algo

import kotlin.test.assertEquals

fun hammingEncode(x: Int, n: Int, r: Int): Int {
    var s = 0
    var j = 0
    var res = 0
    for (i in 1..(n + r)) { // for all bits compute checksum
        if (i and (i - 1) == 0) continue // skip check bit at powers of two
        val bit = (x shr j) and 1 // cur bit of x
        j++
        if (bit != 0) {
            s = s xor i // add bit's number to checksum
            res = res or (1 shl (i - 1)) // res appropriate result bit
        }
    }
    for (i in 0..<r) { // mix in check bits
        val bit = (s shr i) and 1
        if (bit != 0) {
            res = res or (1 shl ((1 shl i) - 1))
        }
    }
    return res
}

fun hammingDecode(x: Int, n: Int, r: Int): Int {
    var s = 0
    for (i in 1..(n + r)) {
        val bit = (x shr (i - 1)) and 1
        if (bit != 0) {
            s = s xor i
        }
    }
    var cor = x
    if (s != 0) { // correct error
        cor = cor xor (1 shl (s - 1))
    }
    var res = 0
    var j = 0
    for (i in 1..(n + r)) { // for all bits extract result
        if (i and (i - 1) == 0) continue // skip check bit at powers of two
        val bit = (cor shr (i - 1)) and 1
        if (bit != 0) {
            res = res or (1 shl j)
        }
        j++
    }
    return res
}

// -----------------------

@OptIn(ExperimentalStdlibApi::class)
fun hammingTest(n: Int, r: Int) {
    for (x in 0..<(1 shl n)) {
        val y = hammingEncode(x, n, r)
        assertEquals(x, hammingDecode(y, n, r), "H($n,$r): ${x.toHexString()} encoded to ${y.toHexString()} decodes")
        for (i in 0..<(n + r)) {
            val z = y xor (1 shl i)
            assertEquals(x, hammingDecode(z, n, r), "H($n,$r): ${x.toHexString()} encoded to ${y.toHexString()} decodes with error at bit $i = ${z.toHexString()}")
        }
    }
}

fun main() {
    hammingTest(4, 3)
    hammingTest(11, 4)
}