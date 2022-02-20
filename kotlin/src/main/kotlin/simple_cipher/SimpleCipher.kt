package simple_cipher

import kotlin.random.Random

internal data class Cipher(val key: String = randomKey()) {

    init {
        require(key.isNotEmpty()) { "key cannot be empty" }
        require(key.all(Char::isLetter)) { "key cannot contain digits" }
        require(key.all(Char::isLowerCase)) { "key cannot contain upper cased letters" }
    }

    fun encode(string: String): String =
        string.mapIndexed { idx, a -> 'a' + (a.ordinal + key[idx % key.length].ordinal) % 26 }.joinToString("")

    fun decode(string: String): String =
        string.mapIndexed { idx, a -> 'a' + (a.ordinal - key[idx % key.length].ordinal + 26) % 26 }.joinToString("")

    companion object {
        private val Char.ordinal: Int get() = lowercaseChar() - 'a'
        private fun randomKey() = buildString(100) { repeat(capacity()) { append(Random.nextInt(97, 123).toChar()) } }
    }

}
