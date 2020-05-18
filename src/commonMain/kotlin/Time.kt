import platform.PlatformInfo
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.nanoseconds

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