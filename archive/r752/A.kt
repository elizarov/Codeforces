fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }.toMutableList()
        loop@while (true) {
            for (i in a.size - 1 downTo 0) {
                if (a[i] % (i + 2) != 0) {
                    a.removeAt(i)
                    continue@loop
                }
            }
            break
        }
        println(if (a.isEmpty()) "YES" else "NO")
    }
}