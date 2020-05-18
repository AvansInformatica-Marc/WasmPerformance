import platform.PlatformInfo
import kotlin.time.*

/*
@ExperimentalTime
@ExperimentalUnsignedTypes
fun main(){
    when (PlatformInfo.platform) {
        is Platform.JavaVirtualMachine -> runWithConsole()
        else -> runWithGUI()
    }
}
*/

/*
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
*/




