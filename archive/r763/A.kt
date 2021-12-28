fun main() {    
    repeat(readLine()!!.toInt()) {
        val (n, m, rb, cb, rd, cd) = readLine()!!.split(" ").map { it.toInt() }
        var step = 0
        var r = rb
        var c = cb
        var dr = 1
        var dc = 1
        while (r != rd && c != cd) {
            step++
            if (r + dr !in 1..n) dr = -dr
            if (c + dc !in 1..m) dc = -dc
            r += dr
            c += dc
        }
        println(step)
    }
}

operator fun List<Int>.component6() = get(5)