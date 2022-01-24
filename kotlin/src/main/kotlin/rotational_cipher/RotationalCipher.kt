package rotational_cipher

internal class RotationalCipher(val key: Int) {

    fun encode(text: String) = buildString(text.length) { text.forEach { append(encode(it)) } }

    private fun encode(char: Char) = if (char.isLetter()) char.offset + (char.ordinal + key) % 26 else char

    companion object {
        private val Char.offset get() = if (isLowerCase()) 'a' else 'A'
        private val Char.ordinal get() = this - offset
    }

}

