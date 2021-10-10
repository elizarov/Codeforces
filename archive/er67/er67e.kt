package archive.er67

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*
import kotlin.math.*

fun main() {
    val n = readLine()!!.toInt()
    val first = IntArray(n + 1)
    val next = IntArray(2 * n)
    val to = IntArray(2 * n)
    var cnt = 1

    fun add(u: Int, v: Int) {
        to[cnt] = v
        next[cnt] = first[u]
        first[u] = cnt
        cnt++
    }


    repeat(n - 1) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() }
        add(u, v)
        add(v, u)
    }

    val size = IntArray(n + 1)
    val dw = LongArray(n + 1)
    val p = IntArray(n + 1)

    val dfs1 = recursiveFunction<Int, Int> { u ->
        var i = first[u]
        var sz = 1
        while (i != 0) {
            val v = to[i]
            if (v != p[u]) {
                p[v] = u
                sz += rec(v)
                dw[u] += dw[v]
            }
            i = next[i]
        }
        size[u] = sz
        dw[u] += sz.toLong()
        sz
    }

    check(dfs1(1) == n)

    val uw = LongArray(n + 1)
    val ss = LongArray(n + 1)

    val dfs2 = recursiveFunction<Int, Unit> { u ->
        var i = first[u]
        if (u != 1) {
            uw[u] = uw[p[u]] + dw[p[u]] - dw[u] - size[u] + ss[p[u]]
            ss[u] = ss[p[u]] + size[p[u]] - size[u]
        }
        while (i != 0) {
            val v = to[i]
            if (v != p[u]) rec(v)
            i = next[i]
        }
    }

    dfs2(1)

    var ans = 0L
    for (u in 1..n) {
        ans = max(ans, dw[u] + uw[u] - size[u] + n)
    }
    println(ans)
}


fun <T, R> recursiveFunction(block: suspend RecursiveFunctionScope<T, R>.(T) -> R): (T) -> R =
    { value ->
        RecursiveFunctionImpl(block, value).call()
    }

@RestrictsSuspension
abstract class RecursiveFunctionScope<T, R> {
    abstract suspend fun rec(value: T): R
}

private class RecursiveFunctionImpl<T, R>(
    private val block: suspend RecursiveFunctionScope<T, R>.(T) -> R,
    private var value: T? = null
) : RecursiveFunctionScope<T, R>(), Continuation<R> {
    private var result: Result<Any?> = Result.success(null)
    private var cont: Continuation<R>? = this

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override fun resumeWith(result: Result<R>) {
        this.cont = null
        this.result = result
    }

    override suspend fun rec(value: T): R = suspendCoroutineUninterceptedOrReturn { cont ->
        this.cont = cont
        this.value = value
        COROUTINE_SUSPENDED
    }

    @Suppress("UNCHECKED_CAST")
    fun call(): R {
        val f = block as Function3<Any?, T?, Continuation<R>?, Any?>
        while (true) {
            val cont = this.cont
                ?: return (result as Result<R>).getOrThrow()
            // This is block.startCoroutine(this, value, cont)
            val r = try {
                f(this, value, cont)
            }  catch (e: Throwable) {
                cont.resumeWithException(e)
                continue
            }
            if (r !== COROUTINE_SUSPENDED)
                cont.resume(r as R)
        }
    }
}

