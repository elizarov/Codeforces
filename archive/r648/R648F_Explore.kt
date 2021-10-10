package archive.r648

fun main() {
    var a = listOf(1, 2, 3, 4)
    val s = HashSet<List<Int>>()
    val n = a.size
    fun print() {
        val new = s.add(a)
        println("${a.joinToString(" ")} $new")
    }
    fun flip(k: Int) {
        a = a.subList(n - k, n) + a.subList(k, n - k) + a.subList(0, k)
    }
    repeat(2 * 3 * 4) {
        print()
        flip(1)
        print()
        flip(2)
    }
}