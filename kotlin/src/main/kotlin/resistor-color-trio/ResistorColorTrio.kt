package `resistor-color-trio`

import `resistor-color-duo`.Color
import kotlin.math.pow

object ResistorColorTrio {

    fun text(vararg colors: Color): String {
        val ohms = colors.map { it.ordinal }.let { (a, b, c) -> (a * 1e1 + b) * 1e1.pow(c) }
        val unit = Unit.values().last { ohms % 1e3.pow(it.ordinal) == 0.0 }
        return "${(ohms / 1e3.pow(unit.ordinal)).toInt()} ${unit.name.toLowerCase()}"
    }

}