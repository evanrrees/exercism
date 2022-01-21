package darts

import kotlin.math.sqrt

object Darts {

    fun <T: Number> score(vararg xy: T): Int {
        val d = xy.map(Number::toDouble).sumOf { it * it }.let(::sqrt)
        return when {
            d <= 1 -> 10
            d <= 5 -> 5
            d <= 10 -> 1
            else -> 0
        }
    }

}
