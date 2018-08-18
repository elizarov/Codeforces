package archive.er47

import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val iFile = File("er47_max.in.txt")
    val oFile = File("er47_max.out.txt")
    val n = 1000000
    iFile.printWriter().use { ouf ->
        ouf.println(n)
        for (i in 1 until n) {
            ouf.println("$i ${i + 1}")
        }
    }
    val time = measureTimeMillis {
        iFile.bufferedReader().use { inf ->
            oFile.printWriter().use { ouf ->
                solveF(inf, ouf)
            }
        }
    }
    println("Time $time ms")

}