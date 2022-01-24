package atbash_cipher

internal object Atbash {

    private const val M = 25

    fun encode(string: String) = decode(string).chunked(5).joinToString(" ")

    fun decode(string: String) = string
        .filter(Char::isLetterOrDigit)
        .map { if (it.isDigit()) it else 'a' + (M - (it.lowercaseChar() - 'a')) }
        .joinToString("")
}
