fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val s = StringBuilder()
        while (s.length < n) {
            println("? ${s}1")
            if (readln().toInt() == 1) {
                s.append(1)
            } else {
                println("? ${s}0")
                if (readln().toInt() == 1) {
                    s.append(0)
                } else {
                    break
                }
            }
        }
        while (s.length < n) {
            println("? 1${s}")
            if (readln().toInt() == 1) {
                s.insert(0, 1)
            } else {
                s.insert(0, 0)
            }
        }

        println("! $s")
    }
}