package platform

import org.w3c.dom.HTMLTableCellElement
import kotlin.browser.document

actual object UserInterface {
    actual interface Element {
        actual fun setText(text: String)
    }

    actual fun getElement(id: String): Element {
        val element = document.getElementById(id)
        return object : Element {
            override fun setText(text: String) {
                (element as? HTMLTableCellElement)?.innerText = text
            }
        }
    }
}