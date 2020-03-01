package archive.kh3

fun main() {
    repeat(readLine()!!.toInt()) {
        when (val n = readLine()!!.toInt()) {
            in 0..999 -> println(n)
            in 1000..999_499 -> println("${(n + 500) / 1000}K")
            else -> println("${(n + 500_000) / 1_000_000}M")
        }
    }
}