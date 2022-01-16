package `armstrong-numbers`

import kotlin.math.pow

object ArmstrongNumber {

    fun check(input: Int) = "$input".run { sumOf { (it - '0').toDouble().pow(length).toInt() } == input }

}
