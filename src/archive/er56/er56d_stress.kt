package archive.er56

import java.io.*
import kotlin.system.*

fun main(args: Array<String>) {
    val n = 300_000
    val fin = File("er56d.in")
    val fout = File("er56d.out")
    fin.printWriter().use { out ->
        out.println(n)
        repeat(n) { out.println("1 0") }
    }
    val out = System.out
    val time = measureTimeMillis {
        fin.inputStream().use {
            System.setIn(it)
            PrintStream(fout).use {
                System.setOut(it)
                bufferOut { er56dMain() }
            }
        }
    }
    System.setOut(out)
    println("Done in $time ms")
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
