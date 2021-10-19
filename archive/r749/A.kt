fun main() {
    val pMax = 100 * 200
    val isPrime = BooleanArray(pMax)
    val pList = ArrayList<Int>()
    fun addPrime(p: Int) {
        isPrime[p] = true
        pList.add(p)
    }
    addPrime(2)
    pLoop@ for (i in 3 until pMax step 2) {
        for (j in pList) {
            if (j * j > i) break
            if (i % j == 0) continue@pLoop
        }
        addPrime(i)
    }
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val sum = a.sum()
        val u = BooleanArray(n)
        fun find(r: Int, s: Int): Boolean {
            if (r == 0) return !isPrime[s]
            for (i in 0 until n) {
                if (!u[i]) {
                    u[i] = true
                    if (find(r - 1, s - a[i])) return true
                    u[i] = false
                }
            }
            return false
        }
        for (rem in 0 until n) {
            if (find(rem, sum)) {
                println(n - rem)
                println((0 until n).filter { !u[it] }.map { it + 1 }.joinToString(" "))
                break
            }
        }
    }
}