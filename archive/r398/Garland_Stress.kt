package archive.r398

fun main() {
    val n = 1_000_000
    val a = IntArray(n + 1)
    val t = IntArray(n + 1)
    for (i in 1..n) {
        a[i] = i - 1
        t[i] = 999
    }
    val timer = algo.Timer()
    val instance = GarlandRec(a, t)
    timer.println("Created")
    timer.println("Solve = ${instance.solve()}")
}