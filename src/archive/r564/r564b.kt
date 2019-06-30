package archive.r564

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

private const val MOD = 998244353

fun main() {
    val n = readLine()!!.toInt()
    val fc = IntArray(n) { -1 }
    val nc = IntArray(2 * n)
    val to = IntArray(2 * n)
    var ec = 0

    fun add(u: Int, v: Int) {
        to[ec] = v
        nc[ec] = fc[u]
        fc[u] = ec
        ec++
    }

    repeat (n - 1) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        add(u, v)
        add(v, u)
    }

    val f = BooleanArray(n)
    val cc = IntArray(n)
    val ss = IntArray(n) { 1 }

    val dfs = recursiveFunction<Int, Int> { u ->
        f[u] = true
        var k = fc[u]
        var result = 1
        while (k >= 0) {
            val v = to[k]
            if (!f[v]) {
                val r = rec(v)
                cc[u]++
                ss[u] += ss[v]
                result = result mul r mul (cc[v] + 1) mul cc[u]
            }
            k = nc[k]
        }
        result
    }

    val r = dfs(0)
    println(r mul n)
}

private infix fun Int.mul(b: Int) = ((toLong() * b) % MOD).toInt()

fun <T, R> recursiveFunction(block: suspend RecursiveFunctionScope<T, R>.(T) -> R): (T) -> R =
    { value ->
        RecursiveFunctionImpl(block, value).call()
    }

@RestrictsSuspension
abstract class RecursiveFunctionScope<T, R> {
    /**
     * Makes recursive call to this [recursiveFunction] putting the call activation frame on the heap,
     * as opposed to the actual call stack that is used by a regular recursive call.
     */
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
