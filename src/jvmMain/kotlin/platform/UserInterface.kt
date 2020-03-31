package platform

actual object UserInterface {
    actual interface Element {
        actual fun setText(text: String)
    }

    actual fun getElement(id: String): Element {
        return object : Element {
            override fun setText(text: String) {
                println("$id -> $text")
            }
        }
    }
}