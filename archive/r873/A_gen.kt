import java.io.*
import kotlin.random.*

fun main() {
    val rnd = Random(1)
    val t = 1
    val n = 200_000
    File("input.txt").printWriter().use { out ->
        out.println(t)
        out.println(n)
        out.println(List(n) { rnd.nextInt(1..1_000_000_00) }.joinToString(" "))
        out.println(List(n) { rnd.nextInt(1..1_000_000_00) }.joinToString(" "))
    }
}