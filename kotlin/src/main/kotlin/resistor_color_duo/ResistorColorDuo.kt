package resistor_color_duo

internal object ResistorColorDuo {

    fun value(vararg colors: Color) = colors.map { it.ordinal }.let { it[0] * 10 + it[1] }

}
