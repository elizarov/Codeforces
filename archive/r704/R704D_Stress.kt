package archive.r704

fun main() {
    for (a in 0..5) {
        for (b in 1..5) {
            val n = a + b
            var maxK = 0
            var xx = 0
            var yy = 0
            for (x in (1 shl (n - 1)) until (1 shl n)) {
                if (x.countOneBits() == b){
                    for (y in (1 shl (n - 1)) until x)
                        if (y.countOneBits() == b) {
                            if ((x - y).countOneBits() > maxK) {
                                maxK = (x - y).countOneBits()
                                xx = x
                                yy = y
                            }
                        }
                }
            }

            println("a = $a, b = $b, maxK = $maxK, " +
                    "x = ${xx.toString(2).padStart(10)}, " +
                    "y = ${yy.toString(2).padStart(10)}, " +
                    "x-y = ${(xx - yy).toString(2).padStart(10)}")
            for (k in 0..maxK) {
                check(solveD(a, b, k) != null)
            }
            check(solveD(a, b, maxK + 1) == null)
        }
    }
}