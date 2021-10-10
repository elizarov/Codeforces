package archive.r554

import java.io.*

fun main() = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    var x = readLine()!!.toInt()
    var t = 0
    val ns = ArrayList<Int>()
    while (true) {
        if (x.isLongCat()) break
        val hPos = x.highestBitNo()
        val masked = ((1 shl (hPos + 1)) - 1).inv() or x
        val zPos = masked.inv().highestBitNo() + 1
        ns.add(zPos)
        x = x xor ((1 shl zPos) - 1)
        t++
        if (x.isLongCat()) break
        x++
        t++
    }
    println(t)
    println(ns.joinToString(" "))
}

private fun Int.highestBitNo() = Integer.numberOfTrailingZeros(Integer.highestOneBit(this))
private fun Int.isLongCat() = this and (this + 1) == 0

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
