package archive.r648

fun main() {
    val a = listOf<Long>(1, 2, 4)
    fun interact(x: List<Int>): Long {
        val b = a.filterIndexed { i, _ -> i in x }.fold(0L, Long::or)
        println("? ${x.size} ${x.joinToString(" ") { (it + 1).toString() }} -> $b")
        return b
    }
    solveG(a.size, ::interact)
}