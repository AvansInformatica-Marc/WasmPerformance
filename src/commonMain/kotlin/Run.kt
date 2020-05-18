import platform.PlatformInfo
import kotlin.time.ExperimentalTime
import kotlin.time.nanoseconds

@ExperimentalTime
val defaultRunner: (RunState) -> Unit = {
    fun log(message: String) = println("${PlatformInfo.platform.name}: $message")

    when(it) {
        is RunState.MethodStarted -> log("Starting calculations...")
        is RunState.FibonacciResult<*> -> log("fibonacci(1000) = ${it.result} (${it.duration})")
        is RunState.FactorialResult<*> -> log("factorial(150) = 150! = ${it.result} (${it.duration})")
        is RunState.MethodResult -> log("Done, total duration time is ${it.duration}")
    }
}

@ExperimentalTime
@ExperimentalUnsignedTypes
fun runPlatformTimedTest(onStateChanged: (RunState) -> Unit) {
    onStateChanged(RunState.MethodStarted)
    val startTime = PlatformInfo.time

    onStateChanged(RunState.FibonacciStarted)
    val (fibResult, fibDuration) = runTimeTest(1000) {
        Math.fibonacci(1000uL)
    }
    onStateChanged(RunState.FibonacciResult(fibResult, fibDuration))

    onStateChanged(RunState.FactorialStarted)
    val (factResult, factDuration) = runTimeTest(10_000) {
        Math.factorial(150uL)
    }
    onStateChanged(RunState.FactorialResult(factResult, factDuration))

    val endTime = PlatformInfo.time
    onStateChanged(RunState.MethodResult((endTime - startTime).toDouble().nanoseconds))
}

@ExperimentalTime
@ExperimentalUnsignedTypes
fun runPlatformTimedTestWithConsole(){
    fun log(message: String) = println("${PlatformInfo.platform.name}: $message")

    log("Hello world!")

    runPlatformTimedTest {
        when(it) {
            is RunState.MethodStarted -> log("Starting calculations...")
            is RunState.FibonacciResult<*> -> log("fibonacci(1000) = ${it.result} (${it.duration})")
            is RunState.FactorialResult<*> -> log("factorial(150) = 150! = ${it.result} (${it.duration})")
            is RunState.MethodResult -> log("Done, total duration time is ${it.duration}")
        }
    }
}