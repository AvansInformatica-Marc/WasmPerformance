package platform

import kotlinx.interop.wasm.dom.document
import kotlinx.wasm.jsinterop.setter

actual object UserInterface {
    actual interface Element {
        actual fun setText(text: String)
    }

    actual fun getElement(id: String): Element {
        val element = document.getElementById(id)
        return object : Element {
            override fun setText(text: String) {
                element.setter("innerText", text)
            }
        }
    }
}