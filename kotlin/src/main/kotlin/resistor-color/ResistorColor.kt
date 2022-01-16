package `resistor-color`

enum class Resistor { BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GREY, WHITE }

object ResistorColor {

    fun colorCode(input: String) = Resistor.valueOf(input.toUpperCase()).ordinal

    fun colors() = Resistor.values().map { it.name.toLowerCase() }

}
