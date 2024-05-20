/**
 * Adapted from Raphael Stäbler's Pappel Node.js framework for Kotlin
 * https://github.com/blazer82/pappel-framework
 */
// package pappel
// import kotlin.coroutines.experimental.*
// import kotlin.js.Promise
/**
 * Async functionality for async/pappel.await ability.
 *
 * Starts an asynchronous execution [block] usually used in an async/pappel.await construct.
 */
function async(block: () => Promise<void>): void {
    block().catch(error => console.error(error));
}
/**
 * Await functionality for async/pappel.await ability.
 *
 * Suspends current asynchronous execution block and awaits the resolution of a Promise<[T]>.
 * Must be used within an [async] block.
 */
async function await<T>(block: () => Promise<T>): Promise<T> {
    try {
        return await block();
    } catch (error) {
        throw error;
    }
}
// private class StandaloneCoroutine(override val context: CoroutineContext): Continuation<Unit> {
//     override fun resume(value: Unit) {}

//     override fun resumeWithException(error: Throwable) {}
// }
// suspend fun <T> Promise<T>.await(): T = suspendCoroutine { cont ->
//     then({ cont.resume(it) }, { cont.resumeWithException(it) })
// }

// fun launch(block: suspend () -> Unit) {
//     block.startCoroutine(object : Continuation<Unit> {
//         override val context: CoroutineContext get() = EmptyCoroutineContext
//         override fun resume(value: Unit) {}
//         override fun resumeWithException(e: Throwable) { console.log("Coroutine failed: $e") }
//     })
// }