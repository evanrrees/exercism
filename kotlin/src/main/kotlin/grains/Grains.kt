package grains

import java.math.BigInteger
import java.math.BigInteger.*

internal object Board {

    private val grains = Array<BigInteger>(64) { ONE }

    fun getGrainCountForSquare(number: Int): BigInteger {
        return if (number == 1)
            grains[0]
        else if (grains[number - 1] != ONE)
            grains[number - 1]
        else {
            val count = getGrainCountForSquare(number - 1) shl 1
            grains[number - 1] = count
            count
        }
    }

    fun getTotalGrainCount() = getGrainCountForSquare(64).shl(1).dec()
}
