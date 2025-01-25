package algo

import kotlin.test.assertEquals

fun <T> prefixFunction(s: List<T>): IntArray {
    val n = s.size
    val p = IntArray(n)
    for (i in 1..<n) {
        var k = p[i - 1]
        while (k > 0 && s[k] != s[i]) k = p[k - 1]
        if (s[k] == s[i]) k += 1
        p[i] = k
    }
    return p
}

fun <T> kmpSearch(s: List<T>, t: List<T>): List<Int> {
    val result = ArrayList<Int>()
    val p = prefixFunction(s)
    var k = 0
    for (i in 0..<t.size) {
        val cur = t[i]
        while (k > 0 && cur != s[k]) k = p[k - 1]
        if (cur == s[k]) k += 1
        if (k == s.size) {
            result += i - k + 1
            k = p[k - 1]
        }
    }
    return result
}

fun main() {
    assertEquals(listOf(0,1,2,3,4), prefixFunction("aaaaa".toList()).toList())
    assertEquals(listOf(0,0,0,0,1,2,3,1,2,3,4,5,6,7,4,5,6), prefixFunction("abcdabcabcdabcdab".toList()).toList())
    assertEquals(listOf(3,11), kmpSearch("aba".toList(), "abcabacaabbabaxabc".toList()))
    assertEquals(listOf(0,15), kmpSearch("abc".toList(), "abcabacaabbabaxabc".toList()))
    assertEquals(listOf(7), kmpSearch("aab".toList(), "abcabacaabbabaxabc".toList()))
}