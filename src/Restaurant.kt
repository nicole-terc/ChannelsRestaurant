import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.runBlocking

@kotlinx.coroutines.ExperimentalCoroutinesApi
fun main() = runBlocking(CoroutineName("main")) {
    println("Restaurant is open")
}
