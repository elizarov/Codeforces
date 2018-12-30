package algo

class Timer {
    val start = System.currentTimeMillis()
    var last = start

    fun println(obj: Any?): Long {
        val time = System.currentTimeMillis()
        kotlin.io.println("${(time - start).toString().padStart(6)}: $obj in ${time - last} ms")
        last = time
        return time
    }
}