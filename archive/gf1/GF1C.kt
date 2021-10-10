package archive.gf1

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, w) = readLine()!!.split(" ").map { it.toInt() }
        val a = listOf(0) + readLine()!!.split(" ").map { it.toInt() } + listOf(w)
        var il = 0
        var xl = 0.0
        var vl = 1
        var ir = n + 1
        var xr = w.toDouble()
        var vr = 1
        var t = 0.0
        while (il + 1 < ir) {
            val dl = a[il + 1] - xl
            val dr = xr - a[ir - 1]
            val dtl = dl / vl
            val dtr = dr / vr
            if (dtl <= dtr) {
                il++
                xl = a[il].toDouble()
                vl++
                xr -= dtl * vr
                t += dtl
            } else {
                ir--
                xr = a[ir].toDouble()
                vr++
                xl += dtr * vl
                t += dtr
            }
        }
        // xl + dt * vl = xr - dt * vr
        // dt * (vl + vr) = xr - xl
        val dt = (xr - xl) / (vl + vr)
        println(t + dt)
    }
}