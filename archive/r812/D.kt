import kotlin.system.*

sealed class Step
class SQ(val a: Int, val b: Int, val l: Step?, val m: Step?, val r: Step?) : Step() {
    override fun toString(): String = "SQ($a,$b,$l,$m,$r)"
}
class AQ(val x: Int) : Step() {
    override fun toString(): String = "AQ($x)"
}

fun main() {
    val s2 = SQ(0,2,SQ(1,2,AQ(2),null,AQ(1)),SQ(1,3,AQ(3),null,AQ(1)),SQ(0,3,AQ(3),null,AQ(0)))
    val s3 = SQ(0,2,SQ(4,6,SQ(2,6,SQ(1,6,AQ(6),null,AQ(1)),SQ(1,5,AQ(5),null,AQ(1)),SQ(2,5,AQ(5),null,AQ(2))),SQ(2,5,SQ(1,5,AQ(5),null,AQ(1)),SQ(1,7,AQ(7),null,AQ(1)),SQ(2,7,AQ(7),null,AQ(2))),SQ(2,4,SQ(1,4,AQ(4),null,AQ(1)),SQ(1,7,AQ(7),null,AQ(1)),SQ(2,7,AQ(7),null,AQ(2)))),SQ(4,6,SQ(1,6,SQ(3,6,AQ(6),null,AQ(3)),SQ(3,5,AQ(5),null,AQ(3)),SQ(1,5,AQ(5),null,AQ(1))),SQ(1,5,SQ(3,5,AQ(5),null,AQ(3)),SQ(3,7,AQ(7),null,AQ(3)),SQ(1,7,AQ(7),null,AQ(1))),SQ(1,4,SQ(3,4,AQ(4),null,AQ(3)),SQ(3,7,AQ(7),null,AQ(3)),SQ(1,7,AQ(7),null,AQ(1)))),SQ(4,6,SQ(0,6,SQ(3,6,AQ(6),null,AQ(3)),SQ(3,5,AQ(5),null,AQ(3)),SQ(0,5,AQ(5),null,AQ(0))),SQ(0,5,SQ(3,5,AQ(5),null,AQ(3)),SQ(3,7,AQ(7),null,AQ(3)),SQ(0,7,AQ(7),null,AQ(0))),SQ(0,4,SQ(3,4,AQ(4),null,AQ(3)),SQ(3,7,AQ(7),null,AQ(3)),SQ(0,7,AQ(7),null,AQ(0)))))
    fun q(a: Int, b: Int): Int {
        println("? $a $b")
        val r = readln().toInt()
        return when(r) {
            1 -> 1
            2 -> -1
            0 -> 0
            else -> exitProcess(0)
        }
    }
    fun exec(ofs: Int, s: Step): Int = when(s) {
        is SQ -> when (q(ofs + s.a, ofs + s.b)) {
            -1 -> exec(ofs, s.l!!)
            1 -> exec(ofs, s.r!!)
            else -> exec(ofs, s.m!!)
        }
        is AQ -> ofs + s.x
    }
    fun best(a: Int, b: Int): Int =
        when (q(a, b)) {
            1 -> a
            -1 -> b
            else -> error("!!!")
        }
    fun solve(ofs: Int, n: Int): Int = when (n) {
        1 -> best(ofs, ofs + 1)
        2 -> exec(ofs, s2)
        3 -> exec(ofs, s3)
        else -> {
            val l = solve(ofs, n - 1)
            val r = solve(ofs + (1 shl (n - 1)), n - 1)
            best(l, r)
        }
    }
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val x = solve(1, n)
        println("! $x")
    }
}

