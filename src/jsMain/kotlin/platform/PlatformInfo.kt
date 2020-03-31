package platform

import kotlin.js.Date

actual object PlatformInfo {
    actual val platform: Platform = Platform.JavaScript

    @ExperimentalUnsignedTypes
    actual val time
        get() = (Date.now() * 1_000_000).toULong()
}