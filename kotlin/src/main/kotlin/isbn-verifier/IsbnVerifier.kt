package `isbn-verifier`

class IsbnVerifier {

    fun isValid(number: String): Boolean = number.filter { it.isDigit() || it == 'X' }.run {
        foldIndexed(0) { i, s, c -> s + (10 - i) * if (c == 'X') 10 else c - '0' }.rem(11) == 0
                && matches(Regex("\\d{9}[\\dX]"))
    }

}
