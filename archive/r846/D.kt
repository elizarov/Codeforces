fun main() {
    repeat(readln().toInt()) {
        var guess = 0
        var cnt = readln().toInt()
        var lastBit = 0
        while (lastBit < cnt) {
            val x = 1 shl lastBit
            println("- $x")
            val c2 = readln().toInt()
            val nb = lastBit + c2 - cnt + 1
            guess = guess or (1 shl nb)
            lastBit = nb
            cnt = c2
        }
        println("! $guess")
    }
}