package platform

import kotlin.system.getTimeNanos

actual object PlatformInfo {
    actual val platform: Platform = Platform.WebAssembly

    @ExperimentalUnsignedTypes
    actual val time
        get() = getTimeNanos().toULong()
}