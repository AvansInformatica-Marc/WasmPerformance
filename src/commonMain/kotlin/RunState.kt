import kotlin.time.Duration
import kotlin.time.ExperimentalTime

sealed class RunState {
    object MethodStarted : RunState()

    object FibonacciStarted : RunState()

    object FactorialStarted : RunState()

    @ExperimentalTime
    data class FibonacciResult<T>(val result: T, val duration: Duration) : RunState()

    @ExperimentalTime
    data class FactorialResult<T>(val result: T, val duration: Duration) : RunState()

    @ExperimentalTime
    data class MethodResult(val duration: Duration) : RunState()
}