import java.io.*

fun main(args: Array<String>) = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }

}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
