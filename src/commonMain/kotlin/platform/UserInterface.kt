package platform

expect object UserInterface {
    interface Element {
        fun setText(text: String)
    }

    fun getElement(id: String): Element
}