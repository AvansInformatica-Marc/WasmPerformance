import platform.Platform
import platform.PlatformInfo
import platform.UserInterface
import kotlin.time.*

@ExperimentalTime
@ExperimentalUnsignedTypes
fun main(){
    when (PlatformInfo.platform) {
        is Platform.JavaVirtualMachine -> runWithConsole()
        else -> runWithGUI()
    }
}

@ExperimentalTime
@ExperimentalUnsignedTypes
fun runWithGUI() {
    val shortcode = when(PlatformInfo.platform) {
        is Platform.WebAssembly -> "wasm32kt"
        is Platform.JavaScript -> "jskt"
        else -> throw IllegalStateException("platform.Platform ${PlatformInfo.platform.name} not implemented.")
    }

    val startTime = PlatformInfo.time

    val totalElement = UserInterface.getElement("$shortcode-total")
    totalElement.setText("Running...")

    val fibElement = UserInterface.getElement("$shortcode-fib")
    fibElement.setText("Running...")
    val (fibResult, fibDuration) = runTimeTest(1000) {
        Math.fibonacci(1000uL)
    }
    fibElement.setText("$fibDuration")
    log("fibonacci(1000) = $fibResult")

    val factElement = UserInterface.getElement("$shortcode-fact")
    factElement.setText("Running...")
    val (factResult, factDuration) = runTimeTest(10_000) {
        Math.factorial(150uL)
    }
    factElement.setText("$factDuration")
    log("factorial(150) = $factResult")

    val endTime = PlatformInfo.time

    totalElement.setText("${(endTime - startTime).toDouble().nanoseconds}")
}

@ExperimentalTime
@ExperimentalUnsignedTypes
fun runWithConsole(){
    log("Hello world!")

    val startTime = PlatformInfo.time

    log("Starting calculations...")

    val (fibResult, fibDuration) = runTimeTest(1_000) {
        Math.fibonacci(1000uL)
    }
    log("fibonacci(1000) = $fibResult (${fibDuration})")

    val (factResult, factDuration) = runTimeTest(10_000) {
        Math.factorial(150uL)
    }
    log("factorial(150) = 150! = $factResult (${factDuration})")

    val endTime = PlatformInfo.time

    log("Done, total duration time is ${(endTime - startTime).toDouble().nanoseconds}")
}

fun log(message: String) = println("${PlatformInfo.platform.name}: $message")

@ExperimentalUnsignedTypes
@ExperimentalTime
inline fun <T> runTimeTest(repeatOperation: Int, block: () -> T): Pair<T, Duration> {
    val startTime = PlatformInfo.time

    repeat(repeatOperation - 1) {
        block()
    }

    val value = block()

    val endTime = PlatformInfo.time

    return value to (endTime - startTime).toDouble().nanoseconds
}