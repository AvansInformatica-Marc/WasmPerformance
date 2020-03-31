package platform

sealed class Platform {
    abstract val name: String

    object WebAssembly : Platform() {
        override val name = "Kotlin/Native (wasm32)"
    }

    object JavaScript : Platform() {
        override val name = "Kotlin/JS"
    }

    object JavaVirtualMachine : Platform() {
        override val name = "Kotlin/JVM"
    }
}