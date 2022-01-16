package acronym

object Acronym {
    fun generate(phrase: String) =
        Regex("\\b[\\w']+\\b").findAll(phrase).joinToString("") { "${it.value.first(Char::isLetter)}" }.toUpperCase()
}
