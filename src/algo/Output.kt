package algo

import java.io.*

private fun bufferOut(block: PrintWriter.() -> Unit) {
    val b = ByteArrayOutputStream()
    val w = PrintWriter(b)
    block(w)
    w.flush()
    System.out.write(b.toByteArray())
}
