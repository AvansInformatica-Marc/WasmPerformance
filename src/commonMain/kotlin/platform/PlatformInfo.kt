package platform

expect object PlatformInfo {
    val platform: Platform

    @ExperimentalUnsignedTypes
    val time: ULong
}