package archive.r530

import java.io.*

fun main(args: Array<String>) = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val t = Array(n) { readLine()!! }
    
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
