package platform

actual object PlatformInfo {
    actual val platform: Platform = Platform.JavaVirtualMachine

    @ExperimentalUnsignedTypes
    actual val time
        get() = System.nanoTime().toULong()
}