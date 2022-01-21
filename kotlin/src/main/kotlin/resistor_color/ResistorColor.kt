package resistor_color

internal enum class Resistor { BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GREY, WHITE }

internal object ResistorColor {

    fun colorCode(input: String) = Resistor.valueOf(input.uppercase()).ordinal

    fun colors() = Resistor.values().map { it.name.lowercase() }

}
