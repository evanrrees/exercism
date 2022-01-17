package crypto_square

object CryptoSquare {

    fun ciphertext(plaintext: String): String {
        val normalized = plaintext.filter(Char::isLetterOrDigit).lowercase()
        return if (normalized.isEmpty()) ""
        else {
            val (r, c) = rc(normalized.length)
            normalized.padEnd(r * c)
                .chunkedSequence(1)
                .chunked(r)
                .reduce { acc, list -> acc.zip(list) { a, b -> a + b } }
                .joinToString(" ")
        }
    }

    tailrec fun rc(target: Int, c: Int = 1, r: Int = 1): Pair<Int, Int> =
        if (r * c >= target) r to c else rc(target, r, c + 1)

}
