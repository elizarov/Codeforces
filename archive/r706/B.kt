package archive.r706

import kotlin.math.*

fun main() {
    val n = readLine()!!.toInt()
    val p = readLine()!!.split(" ").map { it.toInt() }
    var aLen = 0
    var dLen = 0
    data class SG(val aLen: Int, val dLen: Int)
    val sl = ArrayList<SG>()
    for (i in 1 until n) {
        if (p[i] > p[i - 1]) {
            if (dLen > 0) {
                sl += SG(aLen, dLen)
                aLen = 0
                dLen = 0
            }
            aLen++
        } else {
            dLen++
        }
    }
    sl += SG(aLen, dLen)
    val best = sl.maxByOrNull { max(it.aLen, it.dLen) }!!
    val len = max(best.aLen, best.dLen)
    if (sl.any { it != best && (it.aLen >= len || it.dLen >= len) }) {
        println(0)
        return
    }
    if (best.aLen == best.dLen) {
        println(1 - len % 2)
        return
    }
    println(0)
}