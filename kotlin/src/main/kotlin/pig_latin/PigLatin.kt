package pig_latin

internal object PigLatin {

    class PigLatinError(message: String) : Error(message)

    private val uberRegex =
        Regex("((?:[aeiou]+|xr|yt)[A-Za-z]*)|([^aeiou]+)(y[A-Za-z]*)|([A-Za-z])(y)|([^aeiouq]*(?:qu?)?+)([A-Za-z]*)")

    private fun toPig(word: String) =
        uberRegex.matchEntire(word)?.groupValues?.filter(String::isNotBlank)
            ?.let { (it.getOrNull(2) ?: "") + it[1] + "ay" }
            ?: throw PigLatinError(word)

    fun translate(phrase: String) = phrase.split(" ").joinToString(" ") { toPig(it) }

}
