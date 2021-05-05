import kotlin.math.pow

object ArmstrongNumber {

    fun check(input: Int) = "$input".run { sumBy { (it - '0').toDouble().pow(length).toInt() } == input }

}
