package affine_cipher


internal object AffineCipher {

    private const val M = 26

    private val Char.ordinal: Int get() = lowercaseChar() - 'a'

    private val Int.char: Char get() = 'a' + this

    fun encode(input: String, a: Int, b: Int): String {
        require(coprime(a, M)) { "a and m must be coprime"}
        return input.mapNotNull { encode(it, a, b) }.chunked(5) { it.joinToString("") }.joinToString(" ")
    }

    private fun encode(char: Char, a: Int, b: Int) = when {
        char.isLetter() -> ((char.ordinal * a) + b).mod(M).char
        char.isDigit() -> char
        else -> null
    }

    fun decode(input: String, a: Int, b: Int): String {
        require(coprime(a, M)) { "a and m must be coprime"}
        return input.mapNotNull { decode(it, a, b) }.joinToString("")
    }

    private fun decode(char: Char, a: Int, b: Int) = when {
        char.isLetter() -> (mmi(a, M) * (char.ordinal - b)).mod(M).char
        char.isDigit() -> char
        else -> null
    }

}

private tailrec fun mmi(a: Int, n: Int, r: Int = n, newr: Int = a, t: Int = 0, newt: Int = 1): Int {
    return if (newr == 0) {
        require(r <= 1) { "a and m must be coprime." }
        if (t < 0) t + n else t
    } else {
        val quotient = r / newr
        mmi(a, n, newr, r - quotient * newr, newt, t - quotient * newt)
    }
}

private fun coprime(a: Int, other: Int) = gcd(a, other) == 1

private tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
