package archive.er87

fun main() {
    val a = intArrayOf(2, 2, 2, 2, 2, 2, 2, 1)
    val bs = bagSum(a, 1)
    if (bs == null) {
        println("NO")
    } else {
        for (i in a.indices) {
            print(if (bs[i]) a[i] else "-")
            print(" ")
        }
        println()
    }
}