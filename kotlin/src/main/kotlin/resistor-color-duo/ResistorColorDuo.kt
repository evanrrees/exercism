package `resistor-color-duo`

object ResistorColorDuo {

    fun value(vararg colors: Color) = colors.map { it.ordinal }.let { it[0] * 10 + it[1] }

}
