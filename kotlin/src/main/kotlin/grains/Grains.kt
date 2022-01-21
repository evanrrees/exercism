package grains

import java.math.BigInteger
import java.math.BigInteger.*

object Board {

    private val grains = Array<BigInteger>(64) { ONE }

    fun getGrainCountForSquare(number: Int): BigInteger {
        require(number in 1..64)
        return if (number == 1)
            grains[0]
        else if (grains[number - 1] != ONE)
            grains[number - 1]
        else {
            val count = getGrainCountForSquare(number - 1) * TWO
            grains[number - 1] = count
            count
        }
    }

    fun getTotalGrainCount() = getGrainCountForSquare(64) * TWO - ONE
}
