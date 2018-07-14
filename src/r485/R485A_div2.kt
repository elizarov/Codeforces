fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val ac = List(n) { readLine()!! }
    val cs = listOf("purple", "green", "blue", "orange", "red", "yellow")
    val gs = listOf("Power", "Time", "Space", "Soul", "Reality", "Mind")
    val c2g = cs.zip(gs).toMap()
    val ag = ac.map { c2g[it]!! }
    val b = gs.toSet() - ag.toSet()
    println(b.size)
    b.forEach { println(it) }
}